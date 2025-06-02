package umc.spring.service.mission;

import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.MissionRequestDto;
import umc.spring.web.dto.mission.MissionResponseDto;

public interface MissionCommandService {
    public Mission createMission(MissionRequestDto.CreateMissionDto request);
}
