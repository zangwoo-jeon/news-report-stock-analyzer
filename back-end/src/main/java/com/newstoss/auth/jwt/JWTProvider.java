package com.newstoss.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JWTProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long ACCESS_EXPIRATION = 3600000; // 1시간

    public String generateToken(UUID memberId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + ACCESS_EXPIRATION);

        return Jwts.builder()
                .setSubject("ACCESS_TOKEN")
                .claim("memberId", memberId.toString())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public UUID getMemberId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return UUID.fromString(claims.get("memberId", String.class));
    }
}
