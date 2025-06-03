package umc8.spring.web.dto.response;

import lombok.*;
import org.springframework.data.domain.Page;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.domain.mapping.MemberMission;
import umc8.spring.web.dto.MemberMissionDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMissionResponse {

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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionListDto {
        private List<MemberMissionResponse> missions;
        private Integer totalPages;
        private Long totalElements;
        private Boolean isFirst;
    }

    public static MemberMissionListDto fromPage(Page<MemberMissionDto> page) {
        List<MemberMissionResponse> responses = page.stream()
                .map(MemberMissionResponse::from)
                .collect(Collectors.toList());

        return MemberMissionListDto.builder()
                .missions(responses)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .build();
    }

}
