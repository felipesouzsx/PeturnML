package com.felipesouza.peturn.auth;

import com.felipesouza.exceptions.BadCredentialsException;
import com.felipesouza.peturn.user.UserEntity;
import com.felipesouza.peturn.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public void registerUser(RegisterRequest request) throws BadCredentialsException {
        if (userRepository.existsByEmail(request.email())) {
            throw new BadCredentialsException();
        }

        UserEntity newUser = UserEntity.builder()
                .username(request.username())
                .email(request.email())
                .passwordHash(
                        passwordEncoder.encode(request.password())
                )
                .build();
        userRepository.save(newUser);
    }


    public String login(LoginRequest request) throws BadCredentialsException {
        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(BadCredentialsException::new);
        log.info("Found email");

        boolean passwordMatches = passwordEncoder.matches(request.password(), user.getPasswordHash());
        if (!passwordMatches) {
            log.info("Password doesn't match");
            throw new BadCredentialsException();
        }

        log.info("Password matches");
        return jwtService.generateToken(user);
    }
}
