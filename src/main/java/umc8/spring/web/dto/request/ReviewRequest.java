package umc8.spring.web.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewRequest {

    @NotNull
    private Long memberId;
    @NotBlank
    private String body;
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "5.0")
    private Float score;
}
