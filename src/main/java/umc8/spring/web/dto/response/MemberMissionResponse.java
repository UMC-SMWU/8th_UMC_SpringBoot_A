package umc8.spring.web.dto.response;

import lombok.*;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.domain.mapping.MemberMission;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMissionResponse {

    private Long id;
    private String status;

    public static MemberMissionResponse from(MemberMission mm) {
        return MemberMissionResponse.builder()
                .id(mm.getId())
                .status(mm.getStatus().name())
                .build();
    }
}
