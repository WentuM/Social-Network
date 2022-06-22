package ru.kpfu.itis.demo.blog.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.demo.blog.web.dto.UserDTO;
import ru.kpfu.itis.demo.blog.web.jpa.repository.UsersRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return null;
    }


    @Override
    public Optional<UserDTO> findById(Long userId) {
        return usersRepository.findById(userId)
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class));
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

    @Override
    public Boolean update(UserDTO userDTO) {
        return  null;
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class));
    }

//    public Boolean saveLike(UserDTO userDTO, PostDTO postDTO) {
//        Optional<UserEntity> userEntity = usersRepository.findByEmail(userDTO.getUserEmail());
//        userEntity.get().getLikePosts().add(postDTO);
//        usersRepository.save(userEntity.get());
//        return true;
//    }

    

    public void followOnUser(UserDTO userDTO, UserDTO followerDTO) {
        usersRepository.insertAccountIdAndFollowerId(userDTO.getUserId(), followerDTO.getUserId());
    }

    public void deleteFollowOnUser(UserDTO userDTO, UserDTO followerDTO) {
        usersRepository.deleteAccountIdAndFollowerId(userDTO.getUserId(), followerDTO.getUserId());
    }

    @Override
    public UserDTO findByEmailAndPassword(String email, String password) {
        UserDTO userDTO = findByEmail(email).get();
        if (userDTO != null) {
            if (passwordEncoder.matches(password, userDTO.getPassword())) {
                return userDTO;
            }
        }
        return null;
    }
}
