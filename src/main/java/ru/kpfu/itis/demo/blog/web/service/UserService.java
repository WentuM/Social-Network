package ru.kpfu.itis.demo.blog.web.service;

import ru.kpfu.itis.demo.blog.web.dto.UserDTO;

import java.util.Optional;

public interface UserService extends CrudService<UserDTO, Long> {
    Optional<UserDTO> findByEmail(String email);
    void followOnUser(UserDTO userDTO, UserDTO followerDTO);
    void deleteFollowOnUser(UserDTO userDTO, UserDTO followerDTO);
    UserDTO findByEmailAndPassword(String email, String password);
}
