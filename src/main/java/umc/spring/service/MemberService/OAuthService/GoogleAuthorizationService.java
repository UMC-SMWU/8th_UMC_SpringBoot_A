package umc.spring.service.MemberService.OAuthService;

import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
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

import java.net.URI;
import java.util.Collections;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class GoogleAuthorizationService {

    private final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private final String SCOPE = "email%20profile";
    private final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    private final OAuthProperties oAuthProperties;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public String getRedirectUrl(){
        String url = "https://accounts.google.com/o/oauth2/v2/auth?";
        return new StringBuilder(url)
                .append("client_id=")
                .append(oAuthProperties.getGoogle().getClientId())
                .append("&redirect_uri=")
                .append(oAuthProperties.getGoogle().getRedirectUri())
                .append("response_type=code")
                .append("scope=")
                .append(SCOPE)
                .toString();
    }

    public String getGoogleAccessToken(String code){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = Map.of(
                "code", code,
                "client_id", oAuthProperties.getGoogle().getClientId(),
                "client-secret", oAuthProperties.getGoogle().getClientSecret(),
                "redirect-uri", oAuthProperties.getGoogle().getRedirectUri(),
                "grant_type", "authorization_code"
        );

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(TOKEN_URL, params, String.class);

        if (isRequestSuccess(responseEntity)){
            String json = responseEntity.getBody();
            Gson gson = new Gson();
            return gson.fromJson(json, TokenDto.GoogleAccessTokenDto.class)
                    .getAccessToken();
        }
        throw new MemberHandler(ErrorStatus.FAIL_GET_GOOGLE_USER_INFO);
    }

        @Transactional
        public TokenDto.TokenResponseDto googleSignUpOrSignIn(String googleAccessToken){
            MemberResponseDTO.GoogleMemberInfoDto googleMemberInfo = getGoogleMemberInfo(googleAccessToken);

            Member member = memberRepository.findByEmailAndSocialType(googleMemberInfo.getEmail(), "GOOGLE")
                    .orElseGet(() -> memberRepository.save(MemberConverter.toMemberFromGoogle(googleMemberInfo)));

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    member.getEmail(), "",
                    Collections.singleton(() -> member.getRole().name())
            );

            String accessToken = jwtTokenProvider.generateToken(authentication, TokenType.ACCESS);
            String refreshToken = jwtTokenProvider.generateToken(authentication, TokenType.REFRESH);

            member.getRefreshToken().setRefreshValue(refreshToken);

            return TokenConverter.createTokenResponseDto(accessToken, refreshToken);
        }

    // 액세스 토큰을 넣어서 userinfo를 가져온다
    private MemberResponseDTO.GoogleMemberInfoDto getGoogleMemberInfo(String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        String url = new StringBuilder(USER_INFO_URL)
                .append("?access_token=")
                .append(accessToken)
                .toString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        if (isRequestSuccess(responseEntity)) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();
            return gson.fromJson(json, MemberResponseDTO.GoogleMemberInfoDto.class);
        }

        throw new MemberHandler(ErrorStatus.FAIL_GET_GOOGLE_USER_INFO);
    }


    private boolean isRequestSuccess(ResponseEntity<String> responseEntity) {
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

}
