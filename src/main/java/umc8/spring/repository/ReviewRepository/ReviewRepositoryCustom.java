package umc8.spring.repository.ReviewRepository;

import umc8.spring.web.dto.ReviewReplyDto;

import java.util.List;

public interface ReviewRepositoryCustom {
    public List<ReviewReplyDto> findReviewsByStoreId(Long storeId);
}
