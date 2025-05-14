package umc8.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc8.spring.repository.MissionRepository.MemberMissionRepository;
import umc8.spring.web.dto.MemberMissionDto;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;

    public Page<MemberMissionDto> getMyMissions(Long memberId, Pageable pageable) {
        return memberMissionRepository.findMyMissions(memberId, pageable);
    }
}
