package ru.kpfu.itis.demo.blog.api.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserForm {
    @NotBlank(message = "Имя не должно быть пустым")
    private String username;
    @Email(message = "Введите корретный формат почты")
    @NotBlank(message = "Почта не должна быть пустой")
    private String email;
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;
    @NotBlank(message = "Подтверждение пароля не должно быть пустым")
    private String repeat_password;
}
