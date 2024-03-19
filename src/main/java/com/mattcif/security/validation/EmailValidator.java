package com.mattcif.security.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.matches("[A-Za-z0-9._%+-]+@[A-Za-z]+.[A-Za-z]{2,6}$");
    }

    public boolean isValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.matches("[A-Za-z0-9._%+-]+@[A-Za-z]+.[A-Za-z]{2,6}$");
    }
}
