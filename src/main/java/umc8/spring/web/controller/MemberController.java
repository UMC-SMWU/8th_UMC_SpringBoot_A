package umc8.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc8.spring.apiPayload.ApiResponse;
import umc8.spring.converter.MemberConverter;
import umc8.spring.converter.ReviewConverter;
import umc8.spring.domain.Member;
import umc8.spring.domain.Review;
import umc8.spring.service.MemberService.MemberCommandService;
import umc8.spring.service.MemberService.MemberQueryServiceImpl;
import umc8.spring.web.dto.request.MemberRequestDTO;
import umc8.spring.web.dto.response.MemberResponse;
import umc8.spring.web.dto.response.MissionResponse;
import umc8.spring.web.dto.response.ReviewResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryServiceImpl memberQueryServiceImpl;

    @PostMapping("/")
    public ApiResponse<MemberResponse.JoinResultDto> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "회원 ID로 내가 작성한 리뷰 페이징 조회")
    public ApiResponse<ReviewResponse.MyReviewListDto> getMyReviews(
            @PathVariable(name = "memberId") Long memberId,
            @RequestParam(name = "page") Integer page) {
        Page<Review> reviews = memberQueryServiceImpl.getMyReviews(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.myReviewListDto(reviews));
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "회원 ID를 받아 해당 회원이 진행중인 미션 목록 페이징 조회")
    public ApiResponse<MissionResponse> getMyProgressMission() {

        return null;
    }

    @PatchMapping("/{memberId}/missions/{missionId}/complete")
    @Operation(summary = "진행중인 미션 진행 완료로 바꾸기 API", description = "나의 미션 중 미션 ID를 받아 해당 미션을 완료상태로 변경")
    public ApiResponse<MissionResponse> turnMissionComplete() {
        return null;
    }
}
