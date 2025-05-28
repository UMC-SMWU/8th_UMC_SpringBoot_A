package umc8.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc8.spring.apiPayload.ApiResponse;
import umc8.spring.converter.StoreConverter;
import umc8.spring.domain.Review;
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
@Validated
public class StoreController {

    private final StoreQueryServiceImpl storeQueryService;
    private final StoreCommandServiceImpl storeCommandService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponse.StoreResponseDto> addStore(@PathVariable("regionId") Long regionId,
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

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponse.ReviewPreviewListDto> getReviewList(@PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

}
