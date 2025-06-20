package umc8.spring.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import umc8.spring.domain.Review;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.web.dto.MemberMissionDto;
import umc8.spring.web.dto.response.MemberResponse;

public interface MemberQueryService {

    Page<Review> getMyReviews(Long memberId, Integer page);
    Page<MemberMissionDto> getMissionsByStatus(Long memberId, MissionStatus status, Integer page);
    MemberResponse.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
