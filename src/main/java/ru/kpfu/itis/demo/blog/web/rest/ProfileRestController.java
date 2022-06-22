package ru.kpfu.itis.demo.blog.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.demo.blog.web.dto.UserDTO;
import ru.kpfu.itis.demo.blog.web.service.UserService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;
import ru.kpfu.itis.demo.blog.web.security.ouath2.CustomOAuth2User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/profile")
public class ProfileRestController {

    @Autowired
    public UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, String>> getProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, @PathVariable Long userId) {
        UserDTO userDTO;
        if (userDetails == null) {
            userDTO = userService.findByEmail(customOAuth2User.getEmail()).get();
        } else {
            userDTO = userService.findByEmail(userDetails.getEmail()).get();
        }
        UserDTO followerDTO = userService.findById(userId).get();
        Map<String, String> response = new HashMap<>();
        response.put("user", userDTO.toString());
        response.put("follower", followerDTO.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/followUser/{followId}")
    public void followUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, @PathVariable Long followId) {
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
    }

    @DeleteMapping("/{profileId}")
    public void delete(@PathVariable Long profileId) {
        userService.deleteById(profileId);
    }

    @PatchMapping("/{profileId}")
    public void update(@PathVariable Long profileId, @RequestBody String newName) {
        UserDTO userDTO = userService.findById(profileId).get();
        userDTO.setUserName(newName);
        userService.save(userDTO);
    }
}
