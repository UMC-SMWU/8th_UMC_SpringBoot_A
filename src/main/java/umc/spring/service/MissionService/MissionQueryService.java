package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;

public interface MissionQueryService {
    public Page<Mission> getMissionsByStore(Long storeId, Integer page);
    public Page<Mission> getMissionsByStatus(Long memberId, String missionStatus, Integer page);
}
