package umc8.spring.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import umc8.spring.domain.Region;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private Float score;
}
