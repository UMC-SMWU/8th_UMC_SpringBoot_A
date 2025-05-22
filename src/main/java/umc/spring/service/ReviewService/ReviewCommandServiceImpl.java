package umc.spring.service.ReviewService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.review.ReviewRequestDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private ReviewRepository reviewRepository;

    public Review registerReview(ReviewRequestDTO.ReviewRegisterDTO requestDto){
        Review review = ReviewConverter.toReview(requestDto);
        List<ReviewImage> reviewImages = requestDto.getReviewImages().stream()
                .map(reviewImage ->
                        ReviewImage.builder()
                                .review(review)
                                .imageUrl(reviewImage.getImageUrl())
                                .build()
                )
                .toList();
        review.addReviewImage(reviewImages);
        reviewRepository.save(review);
        return review;
    }
}
