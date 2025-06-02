package umc.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.member.MemberRepository;
import umc.spring.repository.mission.MemberMissionRepository;
import umc.spring.repository.mission.MissionRepository;
import umc.spring.repository.store.StoreRepository;
import umc.spring.web.dto.mission.MemberMissionRequestDto;
import umc.spring.web.dto.mission.MissionRequestDto;
import umc.spring.web.dto.mission.MissionResponseDto;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public Mission createMission(MissionRequestDto.CreateMissionDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request,store);
        return missionRepository.save(mission);
    }

    @Transactional
    public MemberMission challengeMission(MemberMissionRequestDto.CreateMemberMissionDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMissionConverter.toMemberMission(request, member, mission);
        return memberMissionRepository.save(memberMission);
    }
}
