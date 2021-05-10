package ru.kpfu.itis.demo.blog.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.demo.blog.api.dto.PostDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.projection.OnlyTittlePost;
import ru.kpfu.itis.demo.blog.impl.service.BlogPostService;
import ru.kpfu.itis.demo.blog.web.exception.PostNotFoundException;
import ru.kpfu.itis.demo.blog.web.security.UserDetailsImpl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/posts")
@RestController
public class PostRestController {

    private final BlogPostService blogPostService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserService userService;

    public PostRestController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<PostDTO> findAll(@RequestParam(required = false) String name, Pageable pageable) {
        return blogPostService.findAllProj();
    }

    @GetMapping("/account/{accountId}")
    public List<PostDTO> findAllByAccount(@PathVariable Long accountId) {
        return blogPostService.findAllByAccountId(accountId);
    }

    @GetMapping("/{id}")
    public PostDTO post(@PathVariable Long id) throws PostNotFoundException {
        return blogPostService.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
    }

    @PatchMapping
    public void updatePost(@RequestBody PostDTO postDTO, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody String newText) {
        postDTO.setBody(newText);
        Date date = new Date();
        postDTO.setUpdatedAt(date);
        blogPostService.save(postDTO);
    }

    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PostDTO postDTO, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("file") MultipartFile file) throws IOException {
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
        Date date = new Date();
        UserDTO userDTO = userService.findByEmail(userDetails.getEmail()).get();
        postDTO.setAccountDto(userDTO);
        postDTO.setCreatedAt(date);
        blogPostService.save(postDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        blogPostService.deleteById(id);
    }

}
