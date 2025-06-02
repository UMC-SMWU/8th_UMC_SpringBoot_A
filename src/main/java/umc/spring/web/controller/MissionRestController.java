package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.mission.MissionCommandService;
import umc.spring.web.dto.mission.MemberMissionRequestDto;
import umc.spring.web.dto.mission.MemberMissionResponseDto;
import umc.spring.web.dto.mission.MissionRequestDto;
import umc.spring.web.dto.mission.MissionResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDto.CreateMissionDto> createMission(@RequestBody @Valid MissionRequestDto.CreateMissionDto createDto) {
        Mission mission = missionCommandService.createMission(createDto);
        return ApiResponse.onSuccess(MissionConverter.toCreateMissionDto(mission));
    }

    @PostMapping("/challenging")
    public ApiResponse<MemberMissionResponseDto.CreateMemberMissionDto> createMemberMission(@RequestBody @Valid MemberMissionRequestDto.CreateMemberMissionDto createDto) {
        MemberMission memberMission = missionCommandService.challengeMission(createDto);
        return ApiResponse.onSuccess(MemberMissionConverter.toCreateChallengingMission(memberMission));
    }
}
