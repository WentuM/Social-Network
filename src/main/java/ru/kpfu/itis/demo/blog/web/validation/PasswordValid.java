package ru.kpfu.itis.demo.blog.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValid {
    String message() default "The password must be at least 6 in length and have one large character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
