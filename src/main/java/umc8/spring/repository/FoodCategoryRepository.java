package umc8.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8.spring.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
