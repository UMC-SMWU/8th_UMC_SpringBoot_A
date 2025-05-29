package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;

import java.util.List;

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

    public static ReviewResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review){
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .owenerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviews){
        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreviewDTOs = reviews.stream()
                .map(ReviewConverter::reviewPreviewDTO)
                .toList();

        return ReviewResponseDTO.ReviewPreviewListDTO.builder()
                .reviews(reviewPreviewDTOs)
                .isLast(reviews.isLast())
                .isFirst(reviews.isFirst())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .listSize(reviews.getSize())
                .build();
    }

}
