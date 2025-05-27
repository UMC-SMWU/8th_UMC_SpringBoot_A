package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.MissionResultDTO> addRegisterMission(MissionRequestDTO.MemberMissionDTO requestDTO) {
        Mission mission = missionCommandService.addMemberMission(requestDTO);
        return ApiResponse.onSuccess(MissionConverter.toMissionResultDTO(mission));
    }

    @GetMapping("/{missionStatus}")
    public ApiResponse<MissionResponseDTO.MissionDTOs> getMissionsByStatus(Long memberId
            , @PathVariable("missionStatus") String missionStatus
            , @RequestParam("page") Integer page) {
        Page<Mission> missionsByStatus = missionQueryService.getMissionsByStatus(memberId, missionStatus, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionDTOs(missionsByStatus));
    }

    @PatchMapping("/{missionId}")
    public ApiResponse<MissionResponseDTO.MissionDTOs> changeStatusToDone(
            Long memberId,
            @PathVariable("missionId") Long missionId
            , @RequestParam("page") Integer page
    ) {
        Page<Mission> missions = missionCommandService.changeStatusToDone(memberId, missionId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionDTOs(missions));
    }


}
