package com.zurum.lanefinance.utils.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=PasswordMatchValidator.class)
public @interface PasswordMatch {
    String message() default "Password and Confirm Password do not match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
