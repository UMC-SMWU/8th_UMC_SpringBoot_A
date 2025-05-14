package umc.spring.converter;

import umc.spring.web.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDto toTempTestDTO(){
        return TempResponse.TempTestDto.builder()
                .testString("This is Test!")
                .build();
    }
}