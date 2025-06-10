package umc.spring.converter;


import umc.spring.web.dto.token.TokenDto;

public class TokenConverter {

    public static TokenDto.TokenResponseDto createTokenResponseDto(String accessToken, String refreshToken){
        return TokenDto.TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
