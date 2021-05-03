package ru.kpfu.itis.demo.blog.api.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long userId;
    private String password;
    private String userName;
    private String userEmail;
    private Set<UserDTO> followUser;
}
