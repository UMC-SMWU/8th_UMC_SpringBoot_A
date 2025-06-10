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




}
