package umc8.spring.web.dto.response;

import lombok.*;
import umc8.spring.domain.Mission;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MissionResponse {
    private Long id;
    private Integer reward;

    public static MissionResponse from(Mission mission) {
        return MissionResponse.builder()
                .id(mission.getId())
                .reward(mission.getReward())
                .build();
    }

}
