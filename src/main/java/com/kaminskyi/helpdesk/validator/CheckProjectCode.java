package com.kaminskyi.helpdesk.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = CheckProjectCodeValidator.class)
public @interface CheckProjectCode {
    String message() default "Код проекту може містити лише латинські літери та цифри";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
