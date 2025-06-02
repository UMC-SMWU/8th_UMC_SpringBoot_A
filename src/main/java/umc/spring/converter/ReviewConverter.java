package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.review.ReviewResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDto.CreateReviewDto toCreateDto(Review review) {
        return ReviewResponseDto.CreateReviewDto.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .build();
    }

    public static Review toReview(ReviewRequestDto.CreateReviewDto request, Member member, Store store) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();
    }

    public static ReviewResponseDto.ReviewPreViewDto reviewPreViewDto(Review review){
        return ReviewResponseDto.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDto.ReviewPreViewListDto reviewPreViewListDto(Page<Review> reviewList){
        List<ReviewResponseDto.ReviewPreViewDto> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDto).collect(Collectors.toList());

        return ReviewResponseDto.ReviewPreViewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
