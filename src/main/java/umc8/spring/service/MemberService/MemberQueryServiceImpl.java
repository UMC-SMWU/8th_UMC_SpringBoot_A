package umc8.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8.spring.apiPayload.code.status.ErrorStatus;
import umc8.spring.apiPayload.exception.handler.MemberHandler;
import umc8.spring.domain.Member;
import umc8.spring.domain.Review;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.domain.mapping.MemberMission;
import umc8.spring.repository.MemberRepository;
import umc8.spring.repository.MissionRepository.MemberMissionRepositoryImpl;
import umc8.spring.repository.ReviewRepository.ReviewRepository;
import umc8.spring.web.dto.MemberMissionDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepositoryImpl memberMissionRepository;

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
}
