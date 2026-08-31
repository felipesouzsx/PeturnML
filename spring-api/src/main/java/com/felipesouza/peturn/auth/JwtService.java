package com.felipesouza.peturn.auth;

import com.felipesouza.peturn.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;
    public static final Duration EXPIRATION_TIME = Duration.ofHours(1);

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(UserEntity user) {
        Date now = new Date();
        // transforma de milissegundos para segundos, depois para minutos, depois para horas.
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME.toMillis());
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
