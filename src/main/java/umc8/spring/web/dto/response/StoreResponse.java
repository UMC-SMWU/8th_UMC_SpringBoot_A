package umc8.spring.web.dto.response;

import lombok.*;
import umc8.spring.domain.Store;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreResponse {

    private Long id;
    private String name;
    private String address;

    public static StoreResponse from(Store store) {
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }
}
