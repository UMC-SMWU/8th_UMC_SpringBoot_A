package umc.spring.web.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionHomeDto {
    private Long missionId;
    private String storeName;
    private Integer reward;
    private String missionSpec;
    private Long daysLeft;
}
