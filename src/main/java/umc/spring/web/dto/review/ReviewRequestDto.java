package umc.spring.web.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStores;

public class ReviewRequestDto {

    @Getter
    public static class CreateReviewDto{
        @NotNull
        @NotBlank
        @Size(min = 1, max = 100)
        String body;

        @NotNull
        Float score;

        @NotNull
        @ExistStores
        Long storeId;

        @NotNull
        Long memberId;
    }
}
