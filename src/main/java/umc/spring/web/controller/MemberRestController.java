package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.TokenConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.ValidPageIndex;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;
import umc.spring.web.dto.token.TokenDto;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final ControllerUtil controllerUtil;
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API", description = "유저가 로그인하는 API입니다.")
    public ApiResponse<TokenDto.TokenResponseDto> login(@RequestBody @Valid MemberRequestDTO.LoginRequestDTO request){
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 API - 인증 필요",
                description = "유저가 내 정보를 조회하는 API입니다.",
                security = {@SecurityRequirement(name = "JWT TOKEN")})
    public ApiResponse<MemberResponseDTO.MemberInfoDTO> getMyInfo(HttpServletRequest request){
        String email = controllerUtil.findMemberByEmail(request);
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(email));
    }

    @GetMapping("/reviews")
    @Operation(summary = "유저 리뷰 조회", description = "유저가 작성한 리뷰를 조회합니다.",
                security = {@SecurityRequirement(name = "JWT TOKEN")})
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getMemberReviews(HttpServletRequest request
            , @RequestParam("page") @ValidPageIndex Integer page){
        String email = controllerUtil.findMemberByEmail(request);
        Page<Review> reviews = reviewCommandService.getMemberReviews(email, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviews));
    }



}
