package umc.spring.web.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionResponseDto {
    private Long missionId;
    private String storeName;
    private Integer reward;
    private String missionSpec;
    private String status;
}
