package umc.spring.service.member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.member.MemberRepository;
import umc.spring.repository.mission.MemberMissionRepository;
import umc.spring.repository.mission.MissionRepository;
import umc.spring.repository.review.ReviewRepository;
import umc.spring.web.dto.member.MemberResponseDto;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Page<Review> getReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<Review> storePage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return storePage;
    }

    public Page<MemberMission> getMissionList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMember(member, PageRequest.of(page, 10));
        return memberMissionPage;
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto.MemberInfoDto getMemberInfo(HttpServletRequest request){
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDto(member);
    }
}
