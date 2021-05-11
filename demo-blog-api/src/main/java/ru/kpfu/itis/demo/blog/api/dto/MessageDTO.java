package ru.kpfu.itis.demo.blog.api.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private String text;
    private String from;
}
