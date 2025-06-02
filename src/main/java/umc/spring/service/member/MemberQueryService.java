package umc.spring.service.member;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;

public interface MemberQueryService {
    Page<Review> getReviewList(Long memberId, Integer page);
    Page<MemberMission> getMissionList(Long memberId, Integer page);
}
