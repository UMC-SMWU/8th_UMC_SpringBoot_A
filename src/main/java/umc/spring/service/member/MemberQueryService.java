package umc.spring.service.member;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.MemberResponseDto;

public interface MemberQueryService {
    Page<Review> getReviewList(Long memberId, Integer page);
    Page<MemberMission> getMissionList(Long memberId, Integer page);
    MemberResponseDto.MemberInfoDto getMemberInfo(HttpServletRequest request);
}
