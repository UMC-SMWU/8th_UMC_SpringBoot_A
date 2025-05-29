package umc.spring.service.MemberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDTO.JoinDto request);
    public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}
