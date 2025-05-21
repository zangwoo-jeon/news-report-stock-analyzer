package com.investinghelper.global.kis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KisTokenResponse {
    private String access_token;
    private String token_type;
    private int expires_in;
}
