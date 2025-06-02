package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.mission.MemberMissionRepository;
import umc.spring.validation.annotation.ExistChallenging;
import umc.spring.web.dto.mission.MemberMissionRequestDto;

@Component
@RequiredArgsConstructor
public class ChallengingExistValidator implements ConstraintValidator<ExistChallenging, MemberMissionRequestDto.CreateMemberMissionDto> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(ExistChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionRequestDto.CreateMemberMissionDto request, ConstraintValidatorContext context) {
        boolean isValid = !memberMissionRepository.existsByMemberIdAndMissionId(
                request.getMemberId(), request.getMissionId()
        );

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.EXIST_CHALLENGE_MISSION.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
