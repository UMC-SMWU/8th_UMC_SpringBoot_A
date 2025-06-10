package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.ValidPageIndex;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/mission")
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;
    private final ControllerUtil controllerUtil;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.MissionResultDTO> addRegisterMission(MissionRequestDTO.MemberMissionDTO requestDTO) {
        Mission mission = missionCommandService.addMemberMission(requestDTO);
        return ApiResponse.onSuccess(MissionConverter.toMissionResultDTO(mission));
    }

    @GetMapping("/{missionStatus}")
    @Operation(summary = "상태에 따른 미션 목록 조회", description = "상태에 따른 미션 목록을 조회하는 API이며, 페이징을 포함합니다. page 번호를 주세요!",
                security = @SecurityRequirement(name ="JWT TOKEN"))
    @Parameters(@Parameter(name = "missionStatus", description = "미션 상태에 따라서 CHALLENGING 혹은 COMPLETE를 주세요!"))
    public ApiResponse<MissionResponseDTO.MissionDTOs> getMissionsByStatus(
            HttpServletRequest requset
            , @PathVariable("missionStatus") String missionStatus
            , @RequestParam("page") @ValidPageIndex Integer page) {
        String email = controllerUtil.findMemberByEmail(requset);
        Page<Mission> missionsByStatus = missionQueryService.getMissionsByStatus(email, missionStatus, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionDTOs(missionsByStatus));
    }

    @PatchMapping("/{missionId}")
    @Operation(summary = "진행 중인 미션 완료 처리", description = "진행 중인 미션을 완료 처리합니다",
                security = {@SecurityRequirement(name ="JWT TOKEN")})
    public ApiResponse<MissionResponseDTO.MissionDTOs> changeStatusToDone(
            HttpServletRequest request
            , @PathVariable("missionId") Long missionId
            , @RequestParam("page") @ValidPageIndex Integer page
    ) {
        String email = controllerUtil.findMemberByEmail(request);
        Page<Mission> missions = missionCommandService.changeStatusToDone(email, missionId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionDTOs(missions));
    }


}
