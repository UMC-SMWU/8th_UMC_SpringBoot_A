package umc.spring.repository.mission;

import umc.spring.web.dto.mission.MissionHomeDto;
import umc.spring.web.dto.mission.MissionResponseDto;

import java.util.List;

public interface MissionRepositoryCustom {
    public List<MissionResponseDto> findOngoingMissions(Long memberId, Long cursor);
    public List<MissionResponseDto> findCompletedMissions(Long memberId, Long cursor);
    public List<MissionHomeDto> findAvailableMissionsByRegion(String regionName, Long memberId, Long cursor);
}
