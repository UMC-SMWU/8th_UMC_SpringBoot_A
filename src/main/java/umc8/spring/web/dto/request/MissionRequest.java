package umc8.spring.web.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MissionRequest {
    private Integer reward;
    private LocalDate deadLine;
    private String missionSpec;
}
