package ru.kpfu.itis.demo.blog.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() > 6 && value.matches(".*[A-Z].*") &&
                value.matches(".*[a-z].*");
    }
}
