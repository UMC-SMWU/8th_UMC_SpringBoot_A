package umc8.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc8.spring.apiPayload.ApiResponse;
import umc8.spring.service.StoreService.StoreCommandServiceImpl;
import umc8.spring.service.StoreService.StoreQueryServiceImpl;
import umc8.spring.web.dto.request.MissionRequest;
import umc8.spring.web.dto.request.ReviewRequest;
import umc8.spring.web.dto.request.StoreRequest;
import umc8.spring.web.dto.response.MemberMissionResponse;
import umc8.spring.web.dto.response.MissionResponse;
import umc8.spring.web.dto.response.ReviewResponse;
import umc8.spring.web.dto.response.StoreResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreQueryServiceImpl storeQueryService;
    private final StoreCommandServiceImpl storeCommandService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponse> addStore(@PathVariable("regionId") Long regionId,
                                               @RequestBody StoreRequest request) {
        return ApiResponse.onSuccess(storeCommandService.createStore(regionId, request));
    }

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponse> addReview(@PathVariable("storeId") Long storeId,
                                                 @RequestBody ReviewRequest request) {
        return ApiResponse.onSuccess(storeCommandService.addReview(storeId, request));
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponse> addMission(@PathVariable("storeId") Long storeId,
                                                   @RequestBody MissionRequest request) {
        return ApiResponse.onSuccess(storeCommandService.addMission(storeId, request));
    }

    @PostMapping("/{storeId}/missions/{missionId}/challenge")
    public ApiResponse<MemberMissionResponse> addChallengeList(@PathVariable("storeId") Long storeId,
                                                               @PathVariable("missionId") Long missionId,
                                                               @RequestParam Long memberId) {
        return ApiResponse.onSuccess(storeCommandService.challengeMission(storeId, missionId, memberId));
    }


}
