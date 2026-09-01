package com.felipesouza.peturn.auth;

import com.felipesouza.exceptions.BadCredentialsException;
import com.felipesouza.peturn.user.UserDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        authService.registerUser(request);
        log.info("Registered user");
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> login(@Valid @RequestBody LoginRequest request) {
        log.info("Logging-in");
        try {
            LoginData response = authService.login(request);
            String token = response.token();
            UserDTO user = response.user();
            ResponseCookie cookie = createCookie("access_token", token, JwtService.EXPIRATION_TIME);
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(user);
        } catch (BadCredentialsException e) {
            log.error("Bad credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    public ResponseEntity<Void> logout() {
        ResponseCookie cookie = createCookie("access_token", "", Duration.ofMinutes(0));
        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }


    private ResponseCookie createCookie(String name, String value, Duration maxAge) {
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(maxAge)
                .build();
    }
}
