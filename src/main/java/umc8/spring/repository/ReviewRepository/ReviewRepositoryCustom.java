package umc8.spring.repository.ReviewRepository;

import java.util.List;

public interface ReviewRepositoryCustom {
    public List<ReviewReplyDto> findReviewsByStoreId(Long storeId);
}
