package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.review.ReviewResponseDto;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDto.CreateResultDto toCreateDto(Store store) {
        return StoreResponseDto.CreateResultDto.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .createAt(store.getCreatedAt())
                .build();
    }

    public static Store toStore(StoreRequestDto.CreateDto request, Region region) {
        return Store.builder()
                .region(region)
                .name(request.getName())
                .address(request.getAddress())
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
                .map(StoreConverter::reviewPreViewDto).collect(Collectors.toList());

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
