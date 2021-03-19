package ru.itis.springlectionsdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class UsersController {

    @GetMapping("/users")
    public String getUsersPage() {
        return "user_page";
    }
}
