package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.demo.blog.web.dto.UserDTO;
import ru.kpfu.itis.demo.blog.web.service.UserService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;
import ru.kpfu.itis.demo.blog.web.security.ouath2.CustomOAuth2User;

@Controller
public class ProfileController {

    @Autowired
    public UserService userService;

    @GetMapping("/profile/{userId}")
    public String getSignUpPage(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model, @PathVariable Long userId) {
        UserDTO userDTO;
        if (userDetails == null) {
            userDTO = userService.findByEmail(customOAuth2User.getEmail()).get();
        } else {
            userDTO = userService.findByEmail(userDetails.getEmail()).get();
        }
        model.addAttribute("user", userDTO);
        UserDTO followerDTO = userService.findById(userId).get();
        model.addAttribute("follower", followerDTO);
        return "home_twitter/profile";
    }

    @PostMapping("/followUser/{followId}")
    public String followUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model, @PathVariable Long followId) {
        UserDTO userDTO;
        if (userDetails == null) {
            userDTO = userService.findByEmail(customOAuth2User.getEmail()).get();
        } else {
            userDTO = userService.findByEmail(userDetails.getEmail()).get();
        }
        UserDTO followerDTO = userService.findById(followId).get();
        try {
            userService.followOnUser(userDTO, followerDTO);
        } catch (Exception e) {
            userService.deleteFollowOnUser(userDTO, followerDTO);
        }
        return "redirect:/home";
    }

    @RequestMapping(value="/ajax/followUser/{followId}", method = RequestMethod.POST)
    public @ResponseBody String followAjaxUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, @PathVariable Long followId) {
        UserDTO userDTO;
        if (userDetails == null) {
            userDTO = userService.findByEmail(customOAuth2User.getEmail()).get();
        } else {
            userDTO = userService.findByEmail(userDetails.getEmail()).get();
        }
        String result = "";
        UserDTO followerDTO = userService.findById(followId).get();
        try {
            userService.followOnUser(userDTO, followerDTO);
            result = "true";
        } catch (Exception e) {
            userService.deleteFollowOnUser(userDTO, followerDTO);
            result = "false";
        }
        return result;
    }
}
