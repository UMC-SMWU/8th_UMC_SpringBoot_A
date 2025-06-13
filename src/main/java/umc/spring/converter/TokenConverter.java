package umc.spring.converter;


import umc.spring.web.dto.token.TokenDto;

public class TokenConverter {

    public static TokenDto.TokenResponseDto createTokenResponseDto(String accessToken, String refreshToken){
        return TokenDto.TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static TokenDto.AccessTokenDto toAccessTokenDto(String accessToken){
        return TokenDto.AccessTokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

    public static TokenDto.TokenResponseDto toTokenResultDTO(Long memberId, String accessToken, String refreshToken) {
        return TokenDto.TokenResponseDto.builder()
                .memberId(memberId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
