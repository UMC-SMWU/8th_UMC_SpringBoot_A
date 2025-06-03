package umc8.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.web.dto.MemberMissionDto;

public interface MemberMissionRepositoryCustom {
    Page<MemberMissionDto> findMyMissions(Long memberId, Pageable pageable);
    Page<MemberMissionDto> findMissionsByStatus(Long memberId, MissionStatus status, Pageable pageable);
}
