package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.demo.blog.api.dto.PostDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.impl.entity.PostEntity;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.service.BlogPostService;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;

import javax.annotation.security.PermitAll;
import javax.mail.Session;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
public class PostController {

    @Value("${upload.path}")
    private String uploadPath;

    private final BlogPostService blogPostService;

    @Autowired
    public UserService userService;

    public PostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PermitAll
    @PostMapping("/savePost")
    public String saveNewPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @ModelAttribute PostDTO postDTO, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            postDTO.setFilename(resultFilename);
        }
        UserDTO userDTO = userService.findByEmail(userDetails.getEmail());
        postDTO.setAccountDto(userDTO);
        blogPostService.save(postDTO);
        return "redirect:/home";
    }

//    @PostMapping("/likePost/{postDTO}")
//    public String likePost(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model, @PathVariable PostDTO postDTO) {
//        UserDTO userDTO = userService.findByEmail(userDetails.getEmail());
//        blogPostService.findAllWithLike(userDTO, postDTO);
////        userService.save(userDTO);
//        return "redirect:/home";
//    }
}
