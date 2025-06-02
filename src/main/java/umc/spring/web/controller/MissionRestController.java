package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public ApiResponse<MemberMissionResponseDto.MemberMissionDto> createMemberMission(@RequestBody @Valid MemberMissionRequestDto.CreateMemberMissionDto createDto) {
        MemberMission memberMission = missionCommandService.challengeMission(createDto);
        return ApiResponse.onSuccess(MemberMissionConverter.toResultMemberMission(memberMission));
    }

    @PostMapping("/complete")
    @Operation(summary = "미션 진행 완료로 변경 API",description = "진행 중인 미션을 진행 완료로 바꾸는 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MemberMissionResponseDto.MemberMissionDto> completeMemberMission(@RequestBody @Valid MemberMissionRequestDto.UpdateMemberMissionDto updateDto) {
        MemberMission memberMission = missionCommandService.completeMission(updateDto);
        return ApiResponse.onSuccess(MemberMissionConverter.toResultMemberMission(memberMission));
    }
}
