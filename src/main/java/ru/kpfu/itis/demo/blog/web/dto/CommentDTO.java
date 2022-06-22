package ru.kpfu.itis.demo.blog.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CommentDTO {
    private Long id;
    @NotBlank(message = "Комментарий не должен быть пустым")
    private String text;
    private Date createdDate;
    private PostDTO post;
    private UserDTO account;
}
