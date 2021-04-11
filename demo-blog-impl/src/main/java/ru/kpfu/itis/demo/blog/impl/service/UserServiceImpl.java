package ru.kpfu.itis.demo.blog.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.UsersRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<UserDTO> findById(Long userId) {
        Optional<UserEntity> user = usersRepository.findById(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.get().getId());
        userDTO.setUserEmail(user.get().getEmail());
        userDTO.setUserName(user.get().getName());
        return Optional.of(userDTO);
    }

    @Override
    public Boolean save(UserDTO userDTO) {
        return null;
    }

    @Override
    public Boolean delete(UserDTO userDTO) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
