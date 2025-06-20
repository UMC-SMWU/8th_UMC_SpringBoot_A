package umc8.spring.service.MemberService;

import umc8.spring.domain.Member;
import umc8.spring.web.dto.request.MemberRequestDTO;
import umc8.spring.web.dto.response.MemberResponse;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);
    MemberResponse.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}
