package umc.spring.web.dto.store;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class StoreRegisterDTO {
        Long regionId;
        String name;
        String address;
    }
}
