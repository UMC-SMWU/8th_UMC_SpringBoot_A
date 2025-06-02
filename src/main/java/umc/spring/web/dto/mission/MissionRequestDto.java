package umc.spring.web.dto.mission;

import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDto {

    @Getter
    public static class CreateMissionDto{
        Integer reward;
        LocalDate deadline;
        @Size(min = 5, max = 20)
        String missionSpec;
        Long storeId;
    }
}
