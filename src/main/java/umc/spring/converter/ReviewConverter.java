package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.review.ReviewResponseDto;

public class ReviewConverter {

    public static ReviewResponseDto.CreateReviewDto toCreateDto(Review review) {
        return ReviewResponseDto.CreateReviewDto.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .build();
    }

    public static Review toReview(ReviewRequestDto.CreateDto request, Member member, Store store) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();
    }
}
