package umc8.spring.web.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class StoreMissionResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class StoreMissionDto {
        private Long id;
        private String missionSpec;
        private Integer reward;
        private LocalDate deadline;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class StoreMissionListDto {
        private List<StoreMissionDto> storeMissions;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}