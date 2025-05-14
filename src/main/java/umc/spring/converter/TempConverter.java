package umc.spring.converter;

import umc.spring.web.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDto toTempTestDTO(){
        return TempResponse.TempTestDto.builder()
                .testString("This is Test!")
                .build();
    }

    public static TempResponse.TempExceptionDto toTempExceptionDTO(Integer flag){
        return TempResponse.TempExceptionDto.builder()
                .flag(flag)
                .build();
    }
}