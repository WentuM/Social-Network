package ru.kpfu.itis.demo.blog.web;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.kpfu.itis.demo.blog.web.dto.PostDTO;
import ru.kpfu.itis.demo.blog.web.service.PostService;
import ru.kpfu.itis.demo.blog.web.entity.PostEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.PostRepository;
import ru.kpfu.itis.demo.blog.web.service.BlogPostService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTest {
    @Test
    void contextLoads() {
    }

    @TestConfiguration
    class PostServiceImplTestContextConfiguration {

        @Bean
        public PostService postService() {
            return new BlogPostService(postRepository, modelMapper);
        }
    }

    @MockBean
    ModelMapper modelMapper;


    @Autowired
    private BlogPostService postService;

    @MockBean
    private PostRepository postRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void whenValidId_thenPostShouldBeFound() {
        Long id = 1L;

        PostEntity postEntity = new PostEntity();
        postEntity.setId(id);

        PostDTO postDTO = new PostDTO();
        postDTO.setId(id);

        when(postRepository.findById(id)).thenReturn(Optional.of(postEntity));
        when(modelMapper.map(postEntity, PostDTO.class)).thenReturn(postDTO);

        assertEquals(postDTO, postService.findById(id).orElseThrow(IllegalStateException::new));
        verify(modelMapper, times(1)).map(postEntity, PostDTO.class);
    }
}