package com.zurum.lanefinance.utils.validations;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "[0-9]{11,13}";
        Pattern pat = Pattern.compile(regex);
        return pat.matcher(phoneNumber).matches();
    }
}
