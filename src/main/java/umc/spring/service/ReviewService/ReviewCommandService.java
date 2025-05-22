package umc.spring.service.ReviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDTO;

public interface ReviewCommandService {
    public Review registerReview(ReviewRequestDTO.ReviewRegisterDTO requestDto);
}
