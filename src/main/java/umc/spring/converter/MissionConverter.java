package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.mission.MissionRequestDto;
import umc.spring.web.dto.mission.MissionResponseDto;

public class MissionConverter {

    public static MissionResponseDto.CreateMissionDto toCreateDto(Mission mission) {
        return MissionResponseDto.CreateMissionDto.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .storeId(mission.getStore().getId())
                .build();
    }

    public static Mission toMission(MissionRequestDto.CreateMissionDto request, Store store) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();
    }
}
