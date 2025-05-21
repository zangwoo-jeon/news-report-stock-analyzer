package com.investinghelper.global.kis;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class KisTokenStore {
    private String token;
    private Instant expiresAt;

    public void store(String token, long expiresInSeconds) {
        this.token = token;
        this.expiresAt = Instant.now().plusSeconds(expiresInSeconds); // 1분 여유

    }

    public String get() {
        if (token == null || Instant.now().isAfter(expiresAt)) {

            throw new IllegalStateException("토큰이 만료됐습니다.");
        }
        return token;
    }
}
