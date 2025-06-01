package umc8.spring.web.dto.response;

import lombok.*;
import umc8.spring.domain.Review;

import java.time.LocalDate;
import java.util.List;

public class ReviewResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class ReviewResponseDto {
        private Long id;
        private Float score;
        private String body;
        private String storeName;
        private LocalDate createdAt;
    }

    public static ReviewResponseDto from(Review review) {
        return ReviewResponse.ReviewResponseDto.builder()
                .id(review.getId())
                .score(review.getScore())
                .body(review.getBody())
                .storeName(review.getStore() != null ? review.getStore().getName() : null)
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toLocalDate() : null)
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class MyReviewListDto {
        private List<ReviewResponseDto> myReviews;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

}
