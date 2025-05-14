package umc8.spring.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {
}
