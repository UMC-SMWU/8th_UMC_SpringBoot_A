package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;

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


}
