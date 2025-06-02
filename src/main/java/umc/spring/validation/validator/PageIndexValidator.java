package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.ValidPageIndex;

@Component
@RequiredArgsConstructor
public class PageIndexValidator implements ConstraintValidator<ValidPageIndex, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = value != null && value >= 1;

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.INVALID_PAGE_INDEX.toString()).addConstraintViolation();
        }

        return false;
    }

    @Override
    public void initialize(ValidPageIndex constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
