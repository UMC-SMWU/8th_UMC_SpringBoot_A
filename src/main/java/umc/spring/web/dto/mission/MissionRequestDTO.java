package umc.spring.web.dto.mission;

import lombok.Getter;
import umc.spring.validation.annotation.NotChallenging;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class MissionRegisterDTO{
        Long storeId;
        Integer reward;
        LocalDate deadLine;
        String missionSpec;
    }

    @Getter
    public static class MemberMissionDTO{
        Long memberId;
        Long missionId;
        @NotChallenging
        String missionStatus;
    }

}
