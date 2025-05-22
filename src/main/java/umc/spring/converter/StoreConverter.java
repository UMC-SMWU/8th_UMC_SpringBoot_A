package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.StoreRegisterDTO requestDTO) {
        return Store.builder()
                .name(requestDTO.getName())
                .address(requestDTO.getAddress())
                .score(0F)
                .build();
    }

    public static StoreResponseDTO.StoreRegisterResultDTO toRegisterResultDto(Store store){
        return StoreResponseDTO.StoreRegisterResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }


}
