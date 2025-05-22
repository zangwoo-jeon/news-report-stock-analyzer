package com.newstoss.global.kis;

import lombok.Data;

@Data
public class KisTokenResponse {
    private String access_token;
    private String token_type;
    private int expires_in;
}
