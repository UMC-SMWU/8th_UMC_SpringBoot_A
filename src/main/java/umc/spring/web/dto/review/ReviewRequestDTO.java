package umc.spring.web.dto.review;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewRegisterDTO{
        String body;
        Float score;
        List<ReviewImageDTO> reviewImages;
//        Member member;
//        Store store;
    }

    @Getter
    public static class ReviewImageDTO{
        String imageUrl;
    }
}
/**
 *     @Builder
 *     private Review(String body, Float score, Member member, Store store) {
 *         this.body = body;
 *         this.score = score;
 *         this.member = member;
 *         this.store = store;
 *     }
 */
