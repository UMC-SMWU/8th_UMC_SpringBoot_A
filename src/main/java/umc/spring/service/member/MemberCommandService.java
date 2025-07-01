package umc.spring.service.member;

import umc.spring.domain.Member;
import umc.spring.web.dto.member.MemberRequestDto;
import umc.spring.web.dto.member.MemberResponseDto;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDto.JoinDto request);
    MemberResponseDto.LoginResultDto loginMember(MemberRequestDto.LoginRequestDto request);
}
