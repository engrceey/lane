package com.zurum.lanefinance.utils.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=PasswordMatchValidator.class)
public @interface PasswordMatch {
    String message() default "Password and Confirm Password do not match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
