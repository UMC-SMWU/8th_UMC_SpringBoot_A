package umc.spring.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewDto {
        Long reviewId;
        Long storeId;
        Long memberId;
    }
}
