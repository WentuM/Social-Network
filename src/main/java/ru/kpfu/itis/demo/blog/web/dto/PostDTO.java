package ru.kpfu.itis.demo.blog.web.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class PostDTO {
    private Long id;
    private String body;
    private Date createdAt;
    private Date updatedAt;
    private String filename;
    private UserDTO accountDto;
    private List<CommentDTO> comments;
    private Set<UserDTO> likePosts;
}
