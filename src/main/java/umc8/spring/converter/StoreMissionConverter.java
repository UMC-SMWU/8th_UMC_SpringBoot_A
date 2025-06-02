package umc8.spring.converter;

import org.springframework.data.domain.Page;
import umc8.spring.domain.Mission;
import umc8.spring.web.dto.response.StoreMissionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class StoreMissionConverter {

    public static StoreMissionResponse.StoreMissionDto toDto(Mission mission) {
        return StoreMissionResponse.StoreMissionDto.builder()
                .id(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadline(mission.getDeadLine())
                .build();
    }

    public static StoreMissionResponse.StoreMissionListDto toMissionListDto(Page<Mission> missions) {
        List<StoreMissionResponse.StoreMissionDto> dtos = missions.stream()
                .map(StoreMissionConverter::toDto)
                .collect(Collectors.toList());

        return StoreMissionResponse.StoreMissionListDto.builder()
                .storeMissions(dtos)
                .listSize(missions.getSize())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }
}
