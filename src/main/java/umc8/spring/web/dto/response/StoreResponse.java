package umc8.spring.web.dto.response;

import lombok.*;
import umc8.spring.domain.Store;

import java.time.LocalDate;
import java.util.List;

public class StoreResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class StoreResponseDto {
        private Long id;
        private String name;
        private String address;
    }

    public static StoreResponseDto from(Store store) {
        return StoreResponseDto.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }

    // 가게의 리뷰 조회 Dto
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class ReviewPreviewListDto {
        private List<ReviewPreviewDto> reviewList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class ReviewPreviewDto {
        private String ownerNickname;
        private Float score;
        private String body;
        private LocalDate createdAt;
    }
}
