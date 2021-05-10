package ru.kpfu.itis.demo.blog.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.demo.blog.api.dto.CommentDTO;
import ru.kpfu.itis.demo.blog.api.dto.PostDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.CommentService;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.impl.entity.CommentEntity;
import ru.kpfu.itis.demo.blog.impl.service.BlogPostService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;
import ru.kpfu.itis.demo.blog.web.security.ouath2.CustomOAuth2User;

import java.util.Date;

@RestController
@RequestMapping("api/posts/{postId}/comments")
public class CommentRestController {

    private final CommentService commentService;

    @Autowired
    public UserService userService;

    @Autowired
    public BlogPostService blogPostService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public Page<CommentDTO> commentsByPost(@PathVariable Long postId, Pageable pageable) {
        return commentService.findAllByPostId(postId, pageable);
    }

    @PostMapping
    public void save(@RequestBody CommentDTO commentDTO) {
        commentService.save(commentDTO);
    }

    @PostMapping("/saveComment/{postId}")
    public void addCommentByPostId(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User customOAuth2User, @RequestBody CommentDTO commentDTO, @PathVariable Long postId) {
        UserDTO userDTO;
        if (userDetails == null) {
            userDTO = userService.findByEmail(customOAuth2User.getEmail()).get();
        } else {
            userDTO = userService.findByEmail(userDetails.getEmail()).get();
        }
        PostDTO postDTO = blogPostService.findById(postId).get();
        commentDTO.setAccount(userDTO);
        commentDTO.setPost(postDTO);
        Date date = new Date();
        commentDTO.setCreatedDate(date);
        commentService.save(commentDTO);
    }

    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Long commentId) {
        commentService.deleteById(commentId);
    }

    @PatchMapping("/{commentId}")
    public void update(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        commentDTO.setId(commentId);
        commentService.save(commentDTO);
    }
}
