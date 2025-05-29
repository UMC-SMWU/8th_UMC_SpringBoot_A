package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.MissionRequestDTO;

public interface MissionCommandService {
    public Mission registerMission(MissionRequestDTO.MissionRegisterDTO requestDTO);
    public Mission addMemberMission(MissionRequestDTO.MemberMissionDTO requestDTO);
    public Page<Mission> changeStatusToDone(String email, Long missionId, Integer page);
}
