package com.example.ott.security;

import com.example.ott.config.JwtProperties;
import com.example.ott.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * Issues and parses three kinds of signed JWTs:
 * access (15 min), refresh (7 days) and stream (5 min, per-video playback token).
 */
@Service
public class JwtService {

    public static final String TYPE_ACCESS = "access";
    public static final String TYPE_REFRESH = "refresh";
    public static final String TYPE_STREAM = "stream";
    public static final Duration STREAM_TTL = Duration.ofMinutes(5);

    private final JwtProperties properties;
    private final SecretKey key;

    public JwtService(JwtProperties properties) {
        this.properties = properties;
        this.key = Keys.hmacShaKeyFor(properties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        return build(user.getEmail(), TYPE_ACCESS, Duration.ofMinutes(properties.getAccessTokenMinutes()),
                Map.of("role", user.getRole().name()));
    }

    public String generateRefreshToken(User user) {
        return build(user.getEmail(), TYPE_REFRESH, Duration.ofDays(properties.getRefreshTokenDays()), Map.of());
    }

    public String generateStreamToken(Long userId, Long videoId) {
        return build(String.valueOf(userId), TYPE_STREAM, STREAM_TTL, Map.of("videoId", videoId));
    }

    public long accessTokenTtlSeconds() {
        return properties.getAccessTokenMinutes() * 60;
    }

    /** @throws io.jsonwebtoken.JwtException if the token is invalid, tampered with or expired */
    public Claims parse(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    private String build(String subject, String type, Duration ttl, Map<String, Object> extraClaims) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(subject)
                .claims(extraClaims)
                .claim("type", type)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(ttl)))
                .signWith(key)
                .compact();
    }
}
