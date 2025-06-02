package umc.spring.web.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.web.dto.review.ReviewResponseDto;

import java.time.LocalDate;
import java.util.List;

public class MemberMissionResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMemberMissionDto {
        Integer status;
        Long memberMissionId;
        Long missionId;
        Long memberId;
    }
}
