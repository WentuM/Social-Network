package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.demo.blog.api.dto.CommentDTO;
import ru.kpfu.itis.demo.blog.api.dto.PostDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.CommentService;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.impl.service.BlogPostService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;

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
    public String addCommentByPostId(@AuthenticationPrincipal UserDetailsImpl userDetails,@ModelAttribute CommentDTO commentDTO, Model model, @PathVariable Long postId) {
//        UserDTO userDTO = userService.findByEmail(userDetails.getEmail());
//        PostDTO postDTO = blogPostService.findById(postId).get();
        commentDTO.setAccountId(userDetails.getId());
//        System.out.println(userDetails.getId());
        commentDTO.setPostId(postId);
//        System.out.println(postId);
        commentService.save(commentDTO);
        return "redirect:/home";
    }
}
