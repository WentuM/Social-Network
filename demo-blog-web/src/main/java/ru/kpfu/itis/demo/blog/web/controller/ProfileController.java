package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getSignUpPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("email", userDetails.getEmail());
        return "home_twitter/profile";
    }
}
