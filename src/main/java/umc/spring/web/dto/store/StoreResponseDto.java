package umc.spring.web.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDto{
        Long storeId;
        String storeName;
        LocalDateTime createAt;
    }
}
