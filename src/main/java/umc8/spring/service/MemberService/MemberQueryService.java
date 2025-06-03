package umc8.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc8.spring.domain.Review;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.web.dto.MemberMissionDto;

public interface MemberQueryService {

    Page<Review> getMyReviews(Long memberId, Integer page);
    Page<MemberMissionDto> getMissionsByStatus(Long memberId, MissionStatus status, Integer page);
}
