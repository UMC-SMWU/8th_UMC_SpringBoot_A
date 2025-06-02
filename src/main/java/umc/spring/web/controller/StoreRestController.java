package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.review.ReviewCommandService;
import umc.spring.service.store.StoreCommandService;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.review.ReviewResponseDto;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDto.CreateResultDto> createStore(@RequestBody @Valid StoreRequestDto.CreateDto createDto) {
        Store store = storeCommandService.createStore(createDto);
        return ApiResponse.onSuccess(StoreConverter.toCreateDto(store));
    }

    @PostMapping("/reviews")
    public ApiResponse<ReviewResponseDto.CreateReviewDto> createReview(@RequestBody @Valid ReviewRequestDto.CreateReviewDto createDto){
        Review review = reviewCommandService.createReview(createDto);
        return ApiResponse.onSuccess(ReviewConverter.toCreateDto(review));
    }
}
