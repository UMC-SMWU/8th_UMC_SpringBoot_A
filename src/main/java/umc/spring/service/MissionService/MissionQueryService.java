package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;

public interface MissionQueryService {
    public Page<Mission> getMissionsByStore(Long storeId, Integer page);
    public Page<Mission> getMissionsByStatus(String email, String missionStatus, Integer page);
}
