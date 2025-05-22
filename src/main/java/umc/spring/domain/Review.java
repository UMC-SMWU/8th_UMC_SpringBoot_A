package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> reviewImages = new ArrayList<>();

    @Builder
    private Review(String body, Float score, Member member, Store store) {
        this.body = body;
        this.score = score;
        this.member = member;
        this.store = store;
    }

    public void addReviewImage(List<ReviewImage> reviewImage) {
        for (ReviewImage image : reviewImage) {
            image.setReview(this);
        }
        this.reviewImages.addAll(reviewImage);
    }
}
