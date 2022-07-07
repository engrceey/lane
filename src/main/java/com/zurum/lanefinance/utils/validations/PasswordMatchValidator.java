package com.zurum.lanefinance.utils.validations;

import com.zurum.lanefinance.dtos.request.UserRegistrationRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRegistrationRequestDto>{

    @Override
    public void initialize(PasswordMatch p) {

    }

    @Override
    public boolean isValid(UserRegistrationRequestDto userRegistrationRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        String plainPassword = userRegistrationRequestDto.getPassword();
        String repeatPassword = userRegistrationRequestDto.getConfirmPassword();

        return plainPassword != null && plainPassword.equals(repeatPassword);
    }

}