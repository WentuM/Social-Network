package ru.kpfu.itis.demo.blog.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {
    private Long id;
    private String tittle;
    private String body;
    private Date createdAt;
    private Date updatedAt;
    private String filename;
    private UserDTO accountDto;
//    private Set<>
//    private Long likes;
//    private Boolean meLiked;
//    private Long accountId;
}
