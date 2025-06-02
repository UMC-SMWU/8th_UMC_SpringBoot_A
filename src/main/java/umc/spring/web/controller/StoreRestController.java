package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.review.ReviewCommandService;
import umc.spring.service.store.StoreCommandService;
import umc.spring.service.store.StoreQueryService;
import umc.spring.validation.annotation.ExistStores;
import umc.spring.validation.annotation.PageValid;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.review.ReviewResponseDto;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;
    private final StoreQueryService storeQueryService;

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

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호입니다. 1부터 시작합니다.")
    })
    public ApiResponse<ReviewResponseDto.ReviewPreViewListDto> getReviewList(@ExistStores @PathVariable(name = "storeId") Long storeId,@PageValid @RequestParam(name = "page") Integer page){
        Page<Review> reviewList =storeQueryService.getReviewList(storeId,page-1);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDto(reviewList));
    }
}
