package com.newstoss.global.kis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KisTokenClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final KisTokenProperties props;

    public KisTokenResponse fetchToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = Map.of(
                "grant_type", props.getGrant_Type(),
                "appkey", props.getAppkey(),
                "appsecret", props.getAppsecret()
        );

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<KisTokenResponse> response = restTemplate.exchange(
                props.getToken_url(),
                HttpMethod.POST,
                entity,
                KisTokenResponse.class
        );

        return response.getBody();
    }

}
