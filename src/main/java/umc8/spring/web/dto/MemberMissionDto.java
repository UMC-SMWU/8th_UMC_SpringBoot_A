package umc8.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.web.dto.response.MemberMissionResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberMissionDto {
    private Long memberMissionId;
    private Long missionId;
    private String missionSpec;
    private String status;
    private Integer reward;
    private String storeName;
    private LocalDate deadLine;

    public static MemberMissionResponse from(MemberMissionDto dto) {
        return MemberMissionResponse.builder()
                .memberMissionId(dto.getMemberMissionId())
                .missionId(dto.getMissionId())
                .missionSpec(dto.getMissionSpec())
                .status(dto.getStatus())
                .reward(dto.getReward())
                .storeName(dto.getStoreName())
                .deadLine(dto.getDeadLine())
                .build();
    }
}
