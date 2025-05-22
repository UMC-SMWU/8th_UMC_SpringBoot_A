package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MissionStatusValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = MissionStatusValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface NotChallenging {
    String message() default "이미 도전 중입니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
