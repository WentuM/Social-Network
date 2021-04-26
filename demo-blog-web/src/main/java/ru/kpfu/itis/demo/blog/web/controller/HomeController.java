package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.demo.blog.api.dto.CommentDTO;
import ru.kpfu.itis.demo.blog.api.dto.PostDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserForm;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.impl.service.BlogCommentService;
import ru.kpfu.itis.demo.blog.impl.service.BlogPostService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;
import ru.kpfu.itis.demo.blog.web.security.ouath2.CustomOAuth2User;

import javax.annotation.security.PermitAll;
import java.awt.print.Pageable;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogCommentService blogCommentService;

    private static UserForm userForm = new UserForm();


    @PermitAll
    @GetMapping("/home")
    public String getHomePage(@AuthenticationPrincipal UserDetailsImpl userDetails,@AuthenticationPrincipal CustomOAuth2User customOAuth2User, Model model) {
        UserDTO userDTO;
        if (userDetails == null) {
            userDTO = userService.findByEmail(customOAuth2User.getEmail()).get();
        } else {
            userDTO = userService.findByEmail(userDetails.getEmail()).get();
        }

        model.addAttribute("userDto", userDTO);

        Iterable<PostDTO> posts = blogPostService.findAllByAccountId(userDTO.getUserId());
        model.addAttribute("posts", posts);

        ArrayList<PostDTO> postFollowers = new ArrayList<>();
        for (UserDTO accountDTO: userDTO.getFollowUser()
             ) {
            ArrayList<PostDTO> postsByAccount = (ArrayList<PostDTO>) blogPostService.findAllByAccountId(accountDTO.getUserId());
            postFollowers.addAll(postsByAccount);
        }
        model.addAttribute("postFollowers", postFollowers);
        return "home_twitter/home";
    }
}
