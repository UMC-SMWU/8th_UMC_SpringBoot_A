package umc.spring.service.mission;

import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.mission.MemberMissionRequestDto;
import umc.spring.web.dto.mission.MissionRequestDto;
import umc.spring.web.dto.mission.MissionResponseDto;

public interface MissionCommandService {
    public Mission createMission(MissionRequestDto.CreateMissionDto request);
    public MemberMission challengeMission(MemberMissionRequestDto.CreateMemberMissionDto request);
}
