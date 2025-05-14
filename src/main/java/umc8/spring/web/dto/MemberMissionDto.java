package umc8.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc8.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberMissionDto {
    private Long memberMissionId;
    private String missionSpec;
    private Integer reward;
    private String storeName;
    private MissionStatus status;
    private LocalDateTime updatedTime;
}
