package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.ReviewRegisterDTO requestDto){
        return Review.builder()
                .build();
    }

    public static ReviewResponseDTO.ReviewRegisterResultDTO toReviewRegisterResultDTO(Review review){
        return ReviewResponseDTO.ReviewRegisterResultDTO.builder()
                .ReviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

}
