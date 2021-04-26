package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.demo.blog.api.dto.PostDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;

import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    public UserService userService;

    @GetMapping("/profile/{userId}")
    public String getSignUpPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model, @PathVariable Long userId) {
        UserDTO userDTO = userService.findById(userDetails.getId()).get();
        model.addAttribute("user", userDTO);
        UserDTO followerDTO = userService.findById(userId).get();
        model.addAttribute("follower", followerDTO);
        return "home_twitter/profile";
    }

    @PostMapping("/followUser/{followId}")
    public String followUser(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model, @PathVariable Long followId) {
        UserDTO userDTO = userService.findByEmail(userDetails.getEmail()).get();
        UserDTO followerDTO = userService.findById(followId).get();
        try {
            userService.followOnUser(userDTO, followerDTO);
        } catch (Exception e) {
            userService.deleteFollowOnUser(userDTO, followerDTO);
        }
        return "redirect:/home";
    }
}
