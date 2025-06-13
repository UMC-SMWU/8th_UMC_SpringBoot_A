package umc.spring.service.MemberService.OAuthService;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.config.properties.OAuthProperties;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.config.security.jwt.TokenType;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.TokenConverter;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.token.TokenDto;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class KakaoAuthorizationService {

    private final OAuthProperties oAuthProperties;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private static final String SCOPE = "profile_nickname,profile_image,account_email";

    public String getRedirectUrl() {
        return new StringBuilder("")
                .append("client_id=")
                .append(oAuthProperties.getKakao().getRestApiKey())
                .append("&redirect_uri=")
                .append(oAuthProperties.getKakao().getRedirectUri())
                .append("&response_type=code")
                .append("&scope=")
                .append(SCOPE)
                .toString();
    }

    public String getKakaoAccessToken(String code){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        OAuthProperties.Kakao kakao = oAuthProperties.getKakao();

        // 메시지 body 설정
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakao.getRestApiKey());
        params.add("redirect_uri", kakao.getRedirectUri());
        params.add("client_secret", kakao.getClientSecret());

        // POST 요청 전송
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, httpEntity, String.class);

        if (isRequestSuccess(responseEntity)) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();

            return gson.fromJson(json, TokenDto.KakaoAccessTokenDto.class)
                    .getAccessToken();
        }
        throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
    }

    public TokenDto.TokenResponseDto kakaoSignUpOrSignIn(String kakaoAccessToken){
        MemberResponseDTO.KakaoMemberInfoDto kakaoMemberInfo = getKakaoMemberInfo(kakaoAccessToken);

        if (!kakaoMemberInfo.isValidatedEmail()){
            throw new MemberHandler(ErrorStatus.FAIL_GET_KAKAO_USER_INFO);
        }

        Member member = memberRepository.findByEmail(kakaoMemberInfo.getKakaoAccount().getEmail())
                .orElseGet(() -> memberRepository.save(MemberConverter.toMemberFromKakao(kakaoMemberInfo)));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), "",
                Collections.singleton(() -> member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication, TokenType.ACCESS);
        String refreshToken = jwtTokenProvider.generateToken(authentication, TokenType.REFRESH);
        member.getRefreshToken().setRefreshValue(refreshToken);

        return TokenConverter.createTokenResponseDto(accessToken, refreshToken);
    }

    public MemberResponseDTO.KakaoMemberInfoDto getKakaoMemberInfo(String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(USER_INFO_URL, HttpMethod.POST, httpEntity, String.class);

        if (isRequestSuccess(responseEntity)){
            String json = responseEntity.getBody();
            Gson gson = new Gson();
            return gson.fromJson(json, MemberResponseDTO.KakaoMemberInfoDto.class);
        }
        throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
    }


    private boolean isRequestSuccess(ResponseEntity<String> responseEntity) {
        return responseEntity.getStatusCode().is2xxSuccessful();
    }



}
