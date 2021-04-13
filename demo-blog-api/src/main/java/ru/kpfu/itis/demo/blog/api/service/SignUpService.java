package ru.kpfu.itis.demo.blog.api.service;

import ru.kpfu.itis.demo.blog.api.dto.UserForm;

public interface SignUpService {
    boolean signUp(UserForm userForm);
}
