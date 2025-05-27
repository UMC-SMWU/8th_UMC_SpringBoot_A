package umc.spring.web.controller;

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
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryServiceImpl;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
@Validated
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryServiceImpl storeQueryServiceImpl;
    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.StoreRegisterResultDTO> registerStore(@RequestBody StoreRequestDTO.StoreRegisterDTO requestDto){
        Store store = storeCommandService.registerStore(requestDto);
        return ApiResponse.onSuccess(StoreConverter.toRegisterResultDto(store));
    }

    @PostMapping("/mission")
    public ApiResponse<MissionResponseDTO.MissionResultDTO> registerMission(@RequestBody MissionRequestDTO.MissionRegisterDTO requestDTO){
        Mission mission = missionCommandService.registerMission(requestDTO);
        MissionResponseDTO.MissionResultDTO missionRegistorDTO = MissionConverter.toMissionResultDTO(mission);
        return ApiResponse.onSuccess(missionRegistorDTO);
    }

    @PostMapping("/review")
    public ApiResponse<ReviewResponseDTO.ReviewRegisterResultDTO> registerReview(@RequestBody ReviewRequestDTO.ReviewRegisterDTO requestDTO){
        Review review = reviewCommandService.registerReview(requestDTO);
        return ApiResponse.onSuccess(ReviewConverter.toReviewRegisterResultDTO(review));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema  = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getReviewListByStore(
            @PathVariable(name = "storeId") Long storeId
            , @RequestParam(name = "page") Integer page){
        Page<Review> reviews = storeQueryServiceImpl.getReviewsByStore(storeId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviews));
    }

    // 원래 이렇게 memberId받으면 안되는데 일단 없으니까..!
    @GetMapping("/reviews/me")
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getMemberReviews(Long memberId, Integer page){
        Page<Review> reviews = reviewCommandService.getMemberReviews(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviews));
    }


    @GetMapping("/{storeId}")
    public ApiResponse<MissionResponseDTO.MissionDTOs> getMissionsByStore(
            @PathVariable("storeId")Long storeId
            , @RequestParam("page") Integer page){
        Page<Mission> missionsByStore = missionQueryService.getMissionsByStore(storeId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionDTOs(missionsByStore));
    }




}
