package umc.spring.repository.member;

import umc.spring.web.dto.member.MemberInfoDto;

public interface MemberRepositoryCustom {
    MemberInfoDto findMemberInfoById(Long memberId);
}
