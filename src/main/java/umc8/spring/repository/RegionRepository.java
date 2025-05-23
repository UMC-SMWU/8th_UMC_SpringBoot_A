package umc8.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8.spring.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
