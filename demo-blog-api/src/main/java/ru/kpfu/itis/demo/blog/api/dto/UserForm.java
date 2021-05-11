package ru.kpfu.itis.demo.blog.api.dto;

import lombok.Data;
import ru.kpfu.itis.demo.blog.api.validation.EmailValid;
import ru.kpfu.itis.demo.blog.api.validation.PasswordValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserForm {
    @NotBlank(message = "Имя не должно быть пустым")
    private String username;
    @Email(message = "Введите корретный формат почты")
    @NotBlank(message = "Почта не должна быть пустой")
    @Email(message = "{errors.incorrect.email}")
    @EmailValid(message = "{errors.repeated.email}")
    private String email;
    @NotBlank(message = "Пароль не должен быть пустым")
    @PasswordValid(message = "{errors.invalid.password}")
    private String password;
    @NotBlank(message = "Подтверждение пароля не должно быть пустым")
    private String repeat_password;
}
