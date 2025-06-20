package umc8.spring.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc8.spring.domain.enums.Role;
import umc8.spring.validator.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDto {
        @NotBlank
        private String name;
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String password;
        @NotNull
        private Integer gender;
        @NotNull
        private Integer birthYear;
        @NotNull
        private Integer birthMonth;
        @NotNull
        private Integer birthDay;
        @Size(min = 5, max = 12)
        private String address;
        @Size(min = 5, max = 12)
        private String specAddress;
        @ExistCategories
        private List<Long> preferCategory;
        @NotNull
        private Role role;
    }

    @Getter
    @Setter
    public static class LoginRequestDTO {

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "패스워드는 필수입니다.")
        private String password;
    }
}
