package umc.spring.repository.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.repository.store.StoreRepositoryCustom;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}
