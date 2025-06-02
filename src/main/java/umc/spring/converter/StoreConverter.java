package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;

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
}
