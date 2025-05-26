package umc8.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8.spring.apiPayload.code.status.ErrorStatus;
import umc8.spring.apiPayload.exception.handler.MemberHandler;
import umc8.spring.apiPayload.exception.handler.MissionHandler;
import umc8.spring.apiPayload.exception.handler.RegionHandler;
import umc8.spring.apiPayload.exception.handler.StoreHandler;
import umc8.spring.domain.*;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.domain.mapping.MemberMission;
import umc8.spring.repository.MemberRepository;
import umc8.spring.repository.MissionRepository.MemberMissionRepository;
import umc8.spring.repository.MissionRepository.MissionRepository;
import umc8.spring.repository.RegionRepository;
import umc8.spring.repository.ReviewRepository.ReviewRepository;
import umc8.spring.repository.StoreRepository.StoreRepository;
import umc8.spring.web.dto.request.MissionRequest;
import umc8.spring.web.dto.request.ReviewRequest;
import umc8.spring.web.dto.request.StoreRequest;
import umc8.spring.web.dto.response.MemberMissionResponse;
import umc8.spring.web.dto.response.MissionResponse;
import umc8.spring.web.dto.response.ReviewResponse;
import umc8.spring.web.dto.response.StoreResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public StoreResponse createStore(Long regionId, StoreRequest request) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        Store store = Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .score(0f)
                .build();

        return StoreResponse.from(storeRepository.save(store));
    }

    @Override
    public ReviewResponse addReview(Long storeId, ReviewRequest request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();
        return ReviewResponse.from(reviewRepository.save(review));
    }

    @Override
    public MissionResponse addMission(Long storeId, MissionRequest request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = Mission.builder()
                .store(store)
                .reward(request.getReward())
                .deadLine(request.getDeadLine())
                .missionSpec(request.getMissionSpec())
                .build();

        return MissionResponse.from(missionRepository.save(mission));
    }

    @Override
    public MemberMissionResponse challengeMission(Long storeId, Long missionId, Long memberId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        return MemberMissionResponse.from(memberMissionRepository.save(memberMission));
    }
}
