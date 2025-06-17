package umc.spring.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.OAuthService.GoogleAuthorizationService;
import umc.spring.service.MemberService.OAuthService.KakaoAuthorizationService;
import umc.spring.web.dto.token.TokenDto;

import static org.springframework.http.HttpStatus.SEE_OTHER;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/authorization")
@RestController
public class AuthorizationRestController {

    private final GoogleAuthorizationService googleAuthorizationService;
    private final KakaoAuthorizationService kakaoAuthorizationService;
    private final MemberCommandService memberCommandService;
    private final ControllerUtil controllerUtil;


    @PostMapping("/google")
    public ApiResponse<TokenDto.TokenResponseDto> googleAuthorization(@RequestBody TokenDto.GoogleAuthorizationCode googleAuthorizationCode) {
        String googleAccessToken = googleAuthorizationService.getGoogleAccessToken(googleAuthorizationCode.getCode());
        TokenDto.TokenResponseDto tokenResponseDto = googleAuthorizationService.googleSignUpOrSignIn(googleAccessToken);
        return ApiResponse.onSuccess(tokenResponseDto);
    }

    @GetMapping("/google/redirection")
    public ResponseEntity<Void> googleRedirect() {
        return ResponseEntity.status(SEE_OTHER)
                .header(HttpHeaders.LOCATION, googleAuthorizationService.getRedirectUrl())
                .build();
    }


    @PostMapping("/kakao")
    public ApiResponse<TokenDto.TokenResponseDto> kakaoAuthorization(@RequestBody TokenDto.KakaoAuthorizationCode kakaoAuthorizationCode) {
        String kakaoAccessToken = kakaoAuthorizationService.getKakaoAccessToken(kakaoAuthorizationCode.getCode());
        TokenDto.TokenResponseDto tokenResponseDto = kakaoAuthorizationService.kakaoSignUpOrSignIn(kakaoAccessToken);
        return ApiResponse.onSuccess(tokenResponseDto);
    }

    @GetMapping("/kakao/redirection")
    public ResponseEntity<Void> kakaoRedirect() {
        return ResponseEntity.status(SEE_OTHER)
                .header(HttpHeaders.LOCATION, kakaoAuthorizationService.getRedirectUrl())
                .build();
    }

    @GetMapping("/reissue")
    public ApiResponse<TokenDto.AccessTokenDto> reissueAccessToken(HttpServletRequest request) {
        String refreshToken = controllerUtil.getTokenFromServletRequest(request);
        TokenDto.AccessTokenDto accessTokenDto = memberCommandService.reissueAccessToken(refreshToken);
        return ApiResponse.onSuccess(accessTokenDto);
    }

}
