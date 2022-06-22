package ru.kpfu.itis.demo.blog.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.demo.blog.web.dto.CommentDTO;

import java.util.List;

public interface CommentService extends CrudService<CommentDTO, Long> {
    Page<CommentDTO> findAllByPostId(Long postId, Pageable pageable);
    List<CommentDTO> findAllByPostIdList(Long postId);
}
