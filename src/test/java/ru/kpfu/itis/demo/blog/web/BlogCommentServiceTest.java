package ru.kpfu.itis.demo.blog.web;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.kpfu.itis.demo.blog.web.dto.CommentDTO;
import ru.kpfu.itis.demo.blog.web.entity.CommentEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.CommentRepository;
import ru.kpfu.itis.demo.blog.web.service.BlogCommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class BlogCommentServiceTest {

    @Test
    void contextLoads() {
    }

    @TestConfiguration
    class PostServiceImplTestContextConfiguration {

        @Bean
        public BlogCommentService blogCommentService() {
            return new BlogCommentService(commentRepository);
        }
    }

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private BlogCommentService blogCommentService;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldSaveNewBlogComment() {
        Long postId = 1L;

        CommentEntity commentEntityList = new CommentEntity();
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        CommentDTO commentDTO = new CommentDTO();

        when(commentRepository.save(modelMapper.map(commentDTO, CommentEntity.class))).thenReturn(commentEntityList);

        assertEquals(true, blogCommentService.save(commentDTO));
    }

    @Test
    public void shouldSaveNewBlogCommentAndFindIt() {
        Long postId = 1L;

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(postId);
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        CommentDTO commentDTO = new CommentDTO();

        when(commentRepository.save(modelMapper.map(commentDTO, CommentEntity.class))).thenReturn(commentEntity);
        when(commentRepository.findById(postId)).thenReturn(Optional.of(commentEntity));

        assertEquals(true, blogCommentService.save(commentDTO));
        assertEquals(Optional.empty(), blogCommentService.findById(postId));
    }
}