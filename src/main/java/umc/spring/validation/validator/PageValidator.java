package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.PageValid;

public class PageValidator implements ConstraintValidator<PageValid, Integer> {

    @Override
    public void initialize(PageValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value > 0) return true;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_OUT_OF_RANGE.toString()).addConstraintViolation();
        return false;
    }
}
