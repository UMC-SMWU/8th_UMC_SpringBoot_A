package umc.spring.repository.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
