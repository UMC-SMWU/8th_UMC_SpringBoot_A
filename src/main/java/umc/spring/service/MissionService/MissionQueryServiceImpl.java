package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

import static umc.spring.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND;
import static umc.spring.apiPayload.code.status.ErrorStatus.STORE_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<Mission> getMissionsByStore(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(STORE_NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page-1, 10);
        return missionRepository.findAllByStore(store, pageRequest);
    }

    @Override
    public Page<Mission> getMissionsByStatus(String email, String missionStatus, Integer page) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(MEMBER_NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page-1, 10);
        return memberMissionRepository.findAllByMemberAndStatus(member, missionStatus, pageRequest)
                .map(MemberMission::getMission);
    }
}
