package umc.spring.web.dto.member;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberInfoDTO {
        String name;
        String email;
        String gender;
    }

    @AllArgsConstructor
    @Getter
    public static class GoogleMemberInfoDto {
        String id;
        String email;
        String name;
        String givenName;
        String familyName;
        @SerializedName("picture")
        String pictureUrl;
        String locale;
    }

    @Getter
    public static class KakaoMemberInfoDto {
        Long id;

        @SerializedName("kako_account")
        KakaoAccount kakaoAccount;

        public Boolean isValidatedEmail(){
            if (kakaoAccount.isEmailValid == null | kakaoAccount.getIsEmailVerified == null) {
                return false;
            }
            return kakaoAccount.isEmailValid && kakaoAccount.getIsEmailVerified;
        }
    }

    @Getter
    public static class KakaoAccount {
        @SerializedName("profile_nickname_needs_agreement")
        private Boolean profileNickanmeNeedsAgreement;
        @SerializedName("profile_image_needs_agreement")
        private Boolean profileImageNeedsAgreement;
        private Profile profile;
        @SerializedName("email_needs_agreement")
        private Boolean emailNeedsAgreement;
        @SerializedName("is_email_valid")
        private Boolean isEmailValid;
        @SerializedName("is_email_verified")
        private Boolean getIsEmailVerified;
        private String email;
    }

    public static class Profile {
        @SerializedName("nickname")
        private String name;
        @SerializedName("thumbnail_image_url")
        private String thumbnailImageUrl;
        @SerializedName("profile_image_url")
        private String profileImageUrl;
        @SerializedName("is_default_image")
        private Boolean isDefaultImage;
        @SerializedName("is_default_nickname")
        private Boolean isDefaultNickname;
    }

}
