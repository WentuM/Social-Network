package ru.kpfu.itis.demo.blog.impl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.kpfu.itis.demo.blog.api.dto.PostDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.PostService;
import ru.kpfu.itis.demo.blog.impl.entity.PostEntity;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.PostRepository;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.UsersRepository;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.projection.OnlyTittlePost;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogPostService implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public BlogPostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<PostDTO> findAll(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class));
//        return postRepository.getPosts()
//                .stream()
//                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
//                .collect(Collectors.toList());
    }

//    public List<PostDTO> findAllWithout() {
//        return postRepository.findAllWithout()
//                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class));
//        return postRepository.getPosts()
//                .stream()
//                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public Optional<PostDTO> findById(Long aLong) {
        return postRepository.findById(aLong)
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class));
    }

    @Override
    public Boolean save(PostDTO postDTO) {
        //?
        postDTO.setId(null);
        PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
        postRepository.save(postEntity);
//        System.out.println(postEntity.getAccount().toString());
        return true;
    }

    @Override
    public Boolean delete(PostDTO postDTO) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        postRepository.deleteById(aLong);
        return false;
    }

    public Page<PostDTO> findAllByName(String name, Pageable pageable) {
        if (StringUtils.hasLength(name)) {
            return postRepository.findAllByTittle(name, pageable)
                    .map(postEntity -> modelMapper.map(postEntity, PostDTO.class));
        } else {
            return this.findAll(pageable);
        }
    }

    public List<PostDTO> findAllProj() {
        return postRepository.findAllByIdIsNotNull().stream().map(
                postEntity -> {
                    PostDTO postDTO = modelMapper.map(postEntity, PostDTO.class);
                    postDTO.setAccountDto(modelMapper.map(postEntity.getAccount(), UserDTO.class));
                    return postDTO;
                }).collect(Collectors.toList());

    }

//    public List<PostDTO> findAllWithLike(UserDTO userDTO, PostDTO postDTO) {
//        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
//        PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
//        Set<UserEntity> likes = postEntity.getLikePosts();
//        if (likes.contains(userEntity)) {
//            likes.remove(userEntity);
//        } else {
//            likes.add(userEntity);
//        }
//        return postRepository.findAll(userEntity);
//    }
}
