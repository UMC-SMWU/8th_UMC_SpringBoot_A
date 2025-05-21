package umc8.spring.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8.spring.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
