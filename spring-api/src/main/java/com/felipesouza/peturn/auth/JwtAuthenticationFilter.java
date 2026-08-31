package com.felipesouza.peturn.auth;

import com.felipesouza.exceptions.UserNotFoundException;
import com.felipesouza.peturn.user.UserEntity;
import com.felipesouza.peturn.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

     public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository, UserDetailsService userDetailsService) {
         this.jwtService = jwtService;
         this.userRepository = userRepository;
         this.userDetailsService = userDetailsService;
     }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException, UserNotFoundException {
        log.info("JWT FILTER: {} {}", request.getMethod(), request.getRequestURI());
        String token = extractToken(request);

        if (token != null) {
            try {
                Claims claims = jwtService.extractClaims(token);
                String userId = claims.getSubject();
                UserEntity user = userRepository.findById(UUID.fromString(userId))
                        .orElseThrow(UserNotFoundException::new);
                UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

                // Credentials é null porque a senha já foi verificada durante o login
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JwtException | IllegalArgumentException e) {
                log.debug("Invalid or expired JWT: {}", e.getMessage());
            } catch (UserNotFoundException e) {
                log.debug("User not found: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
         if (request.getCookies() == null) {
             return null;
         }

         return Arrays.stream(request.getCookies())
                 .filter(cookie -> cookie.getName().equals("access_token"))
                 .map(Cookie::getValue)
                 .findFirst()
                 .orElse(null);
    }
}
