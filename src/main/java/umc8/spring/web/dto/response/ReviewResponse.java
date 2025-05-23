package umc8.spring.web.dto.response;

import lombok.*;
import umc8.spring.domain.Review;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private Float score;
    private String body;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .score(review.getScore())
                .body(review.getBody())
                .build();
    }
}
