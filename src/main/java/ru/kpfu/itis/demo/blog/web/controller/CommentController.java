package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.demo.blog.web.dto.CommentDTO;
import ru.kpfu.itis.demo.blog.web.dto.PostDTO;
import ru.kpfu.itis.demo.blog.web.dto.UserDTO;
import ru.kpfu.itis.demo.blog.web.service.CommentService;
import ru.kpfu.itis.demo.blog.web.service.UserService;
import ru.kpfu.itis.demo.blog.web.service.BlogPostService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;
import ru.kpfu.itis.demo.blog.web.security.ouath2.CustomOAuth2User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public UserService userService;

    @Autowired
    public BlogPostService blogPostService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/saveComment/{postId}")
    public String addCommentByPostId(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, @Valid CommentDTO commentDTO, BindingResult bindingResult, Model model, @PathVariable Long postId) {
        UserDTO userDTO;
        if (userDetails == null) {
            userDTO = userService.findByEmail(customOAuth2User.getEmail()).get();
        } else {
            userDTO = userService.findByEmail(userDetails.getEmail()).get();
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("userDto", userDTO);

            ArrayList<PostDTO> posts = (ArrayList<PostDTO>) blogPostService.findAllByAccountId(userDTO.getUserId());
            for (UserDTO accountDTO: userDTO.getFollowUser()
            ) {
                ArrayList<PostDTO> postsByAccount = (ArrayList<PostDTO>) blogPostService.findAllByAccountId(accountDTO.getUserId());
                posts.addAll(postsByAccount);
            }
            model.addAttribute("posts", posts);

            return "home_twitter/home";
        }
        PostDTO postDTO = blogPostService.findById(postId).get();
        commentDTO.setAccount(userDTO);
        commentDTO.setPost(postDTO);
        Date date = new Date();
        commentDTO.setCreatedDate(date);
        commentService.save(commentDTO);
        return "redirect:/home";
    }
}
