package umc8.spring.converter;

import org.springframework.data.domain.Page;
import umc8.spring.domain.Review;
import umc8.spring.web.dto.response.StoreResponse;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponse.ReviewPreviewDto reviewPreViewDTO(Review review){
        return StoreResponse.ReviewPreviewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponse.ReviewPreviewListDto reviewPreViewListDTO(Page<Review> reviewList){
        List<StoreResponse.ReviewPreviewDto> reviewPreviewDtoList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponse.ReviewPreviewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDtoList.size())
                .reviewList(reviewPreviewDtoList)
                .build();
    }
}
