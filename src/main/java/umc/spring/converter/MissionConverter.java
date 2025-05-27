package umc.spring.converter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

import java.util.List;

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

    public static MissionResponseDTO.MissionDTOs toMissionDTOs(Page<Mission> missions) {
        List<MissionResponseDTO.MissionResultDTO> missionResultDtos = missions.stream()
                .map(MissionConverter::toMissionResultDTO)
                .toList();

        return MissionResponseDTO.MissionDTOs.builder()
                .MissionResultDTOs(missionResultDtos)
                .build();
    }
}
