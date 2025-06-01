package umc8.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc8.spring.domain.Mission;
import umc8.spring.domain.Review;

public interface MemberQueryService {

    Page<Review> getMyReviews(Long memberId, Integer page);
    Page<Mission> getMyProgressMissions(Long memberId, Integer page);
}
