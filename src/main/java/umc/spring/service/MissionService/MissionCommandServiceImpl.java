package umc.spring.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.mission.MissionRequestDTO;

import static umc.spring.apiPayload.code.status.ErrorStatus.*;
import static umc.spring.converter.MissionConverter.toMission;

@RequiredArgsConstructor
@Service
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;


    @Override
    public Mission registerMission(MissionRequestDTO.MissionRegisterDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreId())
                .orElseThrow(() -> new StoreHandler(STORE_NOT_FOUND));
        Mission mission = toMission(requestDTO);
        store.addMission(mission);
        return mission;
    }

    public Mission addMemberMission(MissionRequestDTO.MemberMissionDTO requestDTO) {
        Mission mission = missionRepository.findById(requestDTO.getMemberId())
                .orElseThrow(() -> new MissionHandler(MISSION_NOT_FOUND));
        Member member = memberRepository.findById(requestDTO.getMemberId())
                .orElseThrow(() -> new MemberHandler(MEMBER_NOT_FOUND));
        MemberMission memberMission = MissionConverter.toMemberMission(requestDTO);
        member.addMemberMission(memberMission);
        mission.addMemberMission(memberMission);
        memberRepository.save(member);
        return mission;
    }

    public Page<Mission> changeStatusToDone(String email, Long missionId, Integer page){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(MISSION_NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<MemberMission> challengingMissions = memberMissionRepository.findChallengingMissions(member, mission, pageRequest);
        challengingMissions.forEach(MemberMission::changeStatusToComplete);
        return challengingMissions.map(MemberMission::getMission);
    }

}
