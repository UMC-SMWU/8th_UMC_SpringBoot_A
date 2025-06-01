package umc8.spring.converter;

import org.springframework.data.domain.Page;
import umc8.spring.domain.Review;
import umc8.spring.web.dto.response.ReviewResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponse.ReviewResponseDto reviewResponseDto(Review review) {
        return ReviewResponse.ReviewResponseDto.builder()
                .id(review.getId())
                .score(review.getScore())
                .body(review.getBody())
                .storeName(review.getStore() != null ? review.getStore().getName() : null)
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toLocalDate() : null)
                .build();
    }

    public static ReviewResponse.MyReviewListDto myReviewListDto(Page<Review> reviews) {
        List<ReviewResponse.ReviewResponseDto> reviewResponseDtoList = reviews.stream()
                .map(ReviewConverter::reviewResponseDto)
                .collect(Collectors.toList());

        return ReviewResponse.MyReviewListDto.builder()
                .myReviews(reviewResponseDtoList)
                .listSize(reviews.getSize())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();
    }
}
