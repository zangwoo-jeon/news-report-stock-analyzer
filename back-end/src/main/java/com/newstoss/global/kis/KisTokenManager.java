package com.newstoss.global.kis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KisTokenManager {
    private final KisTokenClient kisTokenClient;
    private final KisTokenStore kisTokenStore;

    public void refresh() {
        log.info("KisTokenManager.refresh() called");
        KisTokenResponse tokenResponse = kisTokenClient.fetchToken();
        kisTokenStore.store(tokenResponse.getAccess_token(), tokenResponse.getExpires_in());
    }

    public String getToken() {
        System.out.println("토큰 요청");
        return kisTokenStore.get();
    }
}
