package ru.kpfu.itis.demo.blog.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.demo.blog.web.dto.CommentDTO;
import ru.kpfu.itis.demo.blog.web.entity.CommentEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.CommentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogCommentService implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BlogCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<CommentDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CommentDTO> findAllByPostId(Long postId, Pageable pageable) {
        return commentRepository.findAllByPostId(postId, pageable).map(commentEntity -> modelMapper.map(commentEntity, CommentDTO.class));
    }

    @Override
    public List<CommentDTO> findAllByPostIdList(Long postId) {
        return commentRepository.findAllByPostId(postId).stream().map(
                commentEntity -> modelMapper.map(commentEntity, CommentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentDTO> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Boolean save(CommentDTO commentDTO) {
        commentRepository.save(modelMapper.map(commentDTO, CommentEntity.class));
        return true;
    }

    @Override
    public Boolean delete(CommentDTO commentDTO) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        commentRepository.deleteById(aLong);
        return true;
    }

    @Override
    public Boolean update(CommentDTO commentDTO) {
        commentRepository.save(modelMapper.map(commentDTO, CommentEntity.class));
        return true;
    }
}
