package umc.spring.web.dto.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDto {

    @Getter
    public static class CreateDto{
        @NotNull
        Long regionId;
        @NotBlank
        String name;
        @Size(min = 5, max = 12)
        String address;

    }
}
