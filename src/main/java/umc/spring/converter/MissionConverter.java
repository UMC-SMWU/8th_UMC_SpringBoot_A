package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.MissionRegisterDTO requestDTO) {
        return Mission.builder()
                .reward(requestDTO.getReward())
                .deadLine(requestDTO.getDeadLine())
                .missionSpec(requestDTO.getMissionSpec())
                .build();
    }

    public static MemberMission toMemberMission(MissionRequestDTO.MemberMissionDTO requestDTO) {
        // 존나 validation
        MissionStatus missionStatus = null;
        switch (requestDTO.getMissionStatus()) {
            case "CHALLENGING":
                missionStatus = MissionStatus.CHALLENGING;
                break;
            case "COMPLETED":
                missionStatus = MissionStatus.COMPLETE;
                break;
            default:
                break;
        }
        return MemberMission.builder()
                .status(missionStatus)
                .build();
    }

    public static MissionResponseDTO.MissionResultDTO toMissionResultDTO(Mission mission) {
        return MissionResponseDTO.MissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
