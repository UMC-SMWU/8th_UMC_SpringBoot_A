package umc.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDTO;

public interface ReviewCommandService {
    public Review registerReview(ReviewRequestDTO.ReviewRegisterDTO requestDto);
    public Page<Review> getMemberReviews(String email, Integer page);
}
