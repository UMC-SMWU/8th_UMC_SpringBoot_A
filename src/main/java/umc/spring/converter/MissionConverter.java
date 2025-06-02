package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.mission.MissionRequestDto;
import umc.spring.web.dto.mission.MissionResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDto.CreateMissionDto toCreateMissionDto(Mission mission) {
        return MissionResponseDto.CreateMissionDto.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .storeId(mission.getStore().getId())
                .build();
    }

    public static Mission toMission(MissionRequestDto.CreateMissionDto request, Store store) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();
    }

    public static MissionResponseDto.MissionPreViewDto missionPreViewDto(Mission mission) {
        return MissionResponseDto.MissionPreViewDto.builder()
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .build();
    }

    public static MissionResponseDto.MissionPreViewListDto missionPreViewListDto(Page<Mission> missionList) {
        List<MissionResponseDto.MissionPreViewDto> missionPreViewDtoList = missionList.stream()
                .map(MissionConverter::missionPreViewDto).collect(Collectors.toList());

        return MissionResponseDto.MissionPreViewListDto.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDtoList.size())
                .missionList(missionPreViewDtoList)
                .build();
    }

    public static MissionResponseDto.MissionPreViewDto memberMissionPreViewDto(MemberMission memberMission) {
        return MissionResponseDto.MissionPreViewDto.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .build();
    }

    public static MissionResponseDto.MissionPreViewListDto memberMissionPreViewListDto(Page<MemberMission> memberMissionList) {
        List<MissionResponseDto.MissionPreViewDto> memberMissionPreViewDtoList = memberMissionList.stream()
                .map(MissionConverter::memberMissionPreViewDto).collect(Collectors.toList());

        return MissionResponseDto.MissionPreViewListDto.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionPreViewDtoList.size())
                .missionList(memberMissionPreViewDtoList)
                .build();
    }
}
