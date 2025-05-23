package umc8.spring.service.StoreService;

import umc8.spring.web.dto.request.MissionRequest;
import umc8.spring.web.dto.request.ReviewRequest;
import umc8.spring.web.dto.request.StoreRequest;
import umc8.spring.web.dto.response.MemberMissionResponse;
import umc8.spring.web.dto.response.MissionResponse;
import umc8.spring.web.dto.response.ReviewResponse;
import umc8.spring.web.dto.response.StoreResponse;

public interface StoreCommandService {
    StoreResponse createStore(Long regionId, StoreRequest request);
    ReviewResponse addReview(Long storeId, ReviewRequest request);
    MissionResponse addMission(Long storeId, MissionRequest request);
    MemberMissionResponse challengeMission(Long storeId, Long missionId, Long memberId);
}
