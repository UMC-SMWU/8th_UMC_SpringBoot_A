package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.mission.MemberMissionRequestDto;
import umc.spring.web.dto.mission.MemberMissionResponseDto;
import umc.spring.web.dto.mission.MissionResponseDto;

public class MemberMissionConverter {

    public static MemberMissionResponseDto.CreateMemberMissionDto toCreateChallengingMission(MemberMission memberMission) {
        return MemberMissionResponseDto.CreateMemberMissionDto.builder()
                .status(memberMission.getStatus().ordinal())
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .build();
    }

    public static MemberMission toMemberMission(MemberMissionRequestDto.CreateMemberMissionDto request, Member member, Mission mission) {
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .member(member)
                .mission(mission)
                .build();
    }

}
