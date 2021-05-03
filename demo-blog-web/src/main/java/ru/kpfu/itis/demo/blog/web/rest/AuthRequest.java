package ru.kpfu.itis.demo.blog.web.rest;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
