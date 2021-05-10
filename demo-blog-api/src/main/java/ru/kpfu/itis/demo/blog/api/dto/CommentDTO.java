package ru.kpfu.itis.demo.blog.api.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

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
