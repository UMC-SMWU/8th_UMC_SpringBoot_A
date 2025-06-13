package umc.spring.web.dto.token;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TokenDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class TokenResponseDto{
        Long memberId;
        String accessToken;
        String refreshToken;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class KakaoAccessTokenDto {
        @SerializedName("access_token")
        private String accessToken;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class GoogleAccessTokenDto {

        @SerializedName("access_token")
        String accessToken;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class AccessTokenDto {
        String accessToken;
    }

    @Getter
    public static class GoogleAuthorizationCode {
        String code;
    }

    @Getter
    public static class KakaoAuthorizationCode {
        String code;
    }


}
