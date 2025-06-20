package umc8.spring.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8.spring.apiPayload.code.status.ErrorStatus;
import umc8.spring.apiPayload.exception.handler.MemberHandler;
import umc8.spring.config.security.jwt.JwtTokenProvider;
import umc8.spring.converter.MemberConverter;
import umc8.spring.domain.Member;
import umc8.spring.domain.Review;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.domain.mapping.MemberMission;
import umc8.spring.repository.MemberRepository;
import umc8.spring.repository.MissionRepository.MemberMissionRepositoryImpl;
import umc8.spring.repository.ReviewRepository.ReviewRepository;
import umc8.spring.web.dto.MemberMissionDto;
import umc8.spring.web.dto.response.MemberResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepositoryImpl memberMissionRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final int PAGE_SIZE = 10;

    @Override
    public Page<Review> getMyReviews(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return reviewRepository.findAllByMember(member, PageRequest.of(page, PAGE_SIZE));
    }

    @Override
    public Page<MemberMissionDto> getMissionsByStatus(Long memberId, MissionStatus status, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        PageRequest pageable = PageRequest.of(page, PAGE_SIZE);

        return memberMissionRepository.findMissionsByStatus(memberId, status, pageable);

    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponse.MemberInfoDTO getMemberInfo(HttpServletRequest request) {
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDTO(member);
    }
}
