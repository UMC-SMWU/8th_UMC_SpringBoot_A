package umc.spring.web.dto.mission;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistChallenging;

public class MemberMissionRequestDto {

    @Getter
    @ExistChallenging
    public static class CreateMemberMissionDto {
        @NotNull
        Long memberId;
        @NotNull
        Long missionId;
    }

    @Getter
    public static class UpdateMemberMissionDto {
        @NotNull
        Long memberId;
        @NotNull
        Long missionId;
    }
}
