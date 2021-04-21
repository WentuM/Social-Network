package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;

@Controller
public class ProfileController {

    @Autowired
    public UserService userService;

    @GetMapping("/profile/{userId}")
    public String getSignUpPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model, @PathVariable Long userId) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("email", userDetails.getEmail());
        model.addAttribute("userId", userDetails.getId());
        UserDTO userDTO = userService.findById(userId).get();
        model.addAttribute("user", userDTO);
        return "home_twitter/profile";
    }
}
