package umc8.spring.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8.spring.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
