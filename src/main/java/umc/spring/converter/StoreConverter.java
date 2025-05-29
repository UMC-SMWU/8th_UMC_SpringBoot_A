package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.review.ReviewResponseDTO;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;


import java.util.List;

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
