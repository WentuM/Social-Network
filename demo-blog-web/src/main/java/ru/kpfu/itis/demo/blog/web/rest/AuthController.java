package ru.kpfu.itis.demo.blog.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.web.security.config.jwt.JwtProvider;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserDTO userDTO = userService.findByEmailAndPassword(request.getLogin(), request.getPassword());
        System.out.println(userDTO);
        String token = jwtProvider.generateToken(userDTO.getUserEmail());
        return new AuthResponse(token);
    }
}
