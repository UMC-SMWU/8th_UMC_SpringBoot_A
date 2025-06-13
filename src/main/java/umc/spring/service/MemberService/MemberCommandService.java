package umc.spring.service.MemberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.token.TokenDto;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDTO.JoinDto request);
    public TokenDto.TokenResponseDto loginMember(MemberRequestDTO.LoginRequestDTO request);

    public TokenDto.AccessTokenDto reissueAccessToken(String refreshToken);
}
