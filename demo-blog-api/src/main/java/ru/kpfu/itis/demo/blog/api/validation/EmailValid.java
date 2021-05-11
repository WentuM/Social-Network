package ru.kpfu.itis.demo.blog.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//должно быть в module Impl, но пришлось сюда закинуть из-за циклического импорта(userform)

@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValid {
    String message() default "User with such mail already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
