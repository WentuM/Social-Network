package ru.kpfu.itis.demo.blog.api.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.demo.blog.api.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValid, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userService.findByEmail(value).isPresent();
    }
}
