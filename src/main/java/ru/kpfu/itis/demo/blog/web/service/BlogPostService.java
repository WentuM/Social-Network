package ru.kpfu.itis.demo.blog.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.demo.blog.web.dto.PostDTO;
import ru.kpfu.itis.demo.blog.web.dto.UserDTO;
import ru.kpfu.itis.demo.blog.web.entity.PostEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.PostRepository;

import java.util.*;
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
        postDTO.setId(null);
        PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
        postRepository.save(postEntity);
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

    @Override
    public Boolean update(PostDTO postDTO) {
        PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
        postRepository.save(postEntity);
        return true;
    }

//    public Page<PostDTO> findAllByName(String name, Pageable pageable) {
//        if (StringUtils.hasLength(name)) {
//            return postRepository.findAllByTittle(name, pageable)
//                    .map(postEntity -> modelMapper.map(postEntity, PostDTO.class));
//        } else {
//            return this.findAll(pageable);
//        }
//    }


//    public List<PostDTO> findAllPostByFollowers(Set<UserDTO> usersDTO) {
////        Set<UserEntity> postFollowersEntity = postFollowers.stream().map(userDTO -> modelMapper.map(userDTO, UserEntity.class)).collect(Collectors.toSet());
////        return postRepository.findPostEntitiesByAccountFollowUser(postFollowersEntity).stream().map(
////                postEntity -> {
////                    PostDTO postDTO = modelMapper.map(postEntity, PostDTO.class);
////                    postDTO.setAccountDto(modelMapper.map(postEntity.getAccount(), UserDTO.class));
////                    return postDTO;
////                }).collect(Collectors.toList());
//        ArrayList<Long> usersId = new ArrayList<>();
//        for (UserDTO userDTO : usersDTO
//        ) {
//            usersId.add(userDTO.getUserId());
//        }
//        System.out.println(usersId.toString());
//        return postRepository.findPostEntitiesByAccountFollowUserId(usersId).stream().map(
//                postEntity -> modelMapper.map(postEntity, PostDTO.class)
//        ).collect(Collectors.toList());
//    }

    public List<PostDTO> findAllProj() {
        return postRepository.findAllByIdIsNotNull().stream().map(
                postEntity -> {
                    PostDTO postDTO = modelMapper.map(postEntity, PostDTO.class);
                    postDTO.setAccountDto(modelMapper.map(postEntity.getAccount(), UserDTO.class));
                    return postDTO;
                }).collect(Collectors.toList());
    }

    public List<PostDTO> findAllByAccountId(Long accountId) {
        return postRepository.findAllByAccount_Id(accountId).stream().map(
                postEntity -> {
                    PostDTO postDTO = modelMapper.map(postEntity, PostDTO.class);
                    postDTO.setAccountDto(modelMapper.map(postEntity.getAccount(), UserDTO.class));
                    return postDTO;
                }).collect(Collectors.toList());
    }

    public void findAllWithLike(UserDTO userDTO, PostDTO postDTO) {
        postRepository.insertPostIdAndAccountId(postDTO.getId(), userDTO.getUserId());
    }

    public void deleteAllWithLike(UserDTO userDTO, PostDTO postDTO) {
        postRepository.deletePostIdAndAccountId(postDTO.getId(), userDTO.getUserId());
    }
}
