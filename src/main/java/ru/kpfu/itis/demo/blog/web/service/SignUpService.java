package ru.kpfu.itis.demo.blog.web.service;


import ru.kpfu.itis.demo.blog.web.dto.UserForm;

public interface SignUpService {
    boolean signUp(UserForm userForm);
    void signUpOauth(String email, String name);
}
