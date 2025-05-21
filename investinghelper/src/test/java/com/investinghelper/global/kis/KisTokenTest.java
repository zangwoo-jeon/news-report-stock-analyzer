package com.investinghelper.global.kis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KisTokenTest {
    private static final Logger log = LoggerFactory.getLogger(KisTokenTest.class);
    @Autowired KisTokenProperties kisProperties;
    @Autowired KisTokenManager kisTokenManager;
    private final RestTemplate restTemplate = new RestTemplate();
    @Test
    public void getAllStockItems() {
        String token = kisTokenManager.getToken(); // 🔥 여기서 토큰 가져옴

        String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", "Bearer " + token);
        headers.set("appkey", kisProperties.getAppkey());
        headers.set("appsecret", kisProperties.getAppsecret());
        headers.set("tr_id", "FHKST01010100");
        headers.set("custtype", "P");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("FID_COND_MRKT_DIV_CODE", "J")
                .queryParam("FID_INPUT_ISCD", "005930");

        ResponseEntity<KisResponse> response = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                entity,
                KisResponse.class
        );
        assertThat(response.getBody()).isNotNull();
        StockItem stockItem = response.getBody().getStockItem();
        System.out.println("stockItem.getStockName() = " + stockItem.getStockName());
        System.out.println("stockItem.getMarketName() = " + stockItem.getMarketName());
        System.out.println("stockItem.getStockPrice() = " + stockItem.getStockPrice());

    }
    static class KisResponse {
        @JsonProperty("output")
        private StockItem stockItem;
        public KisResponse() {
        }
        public StockItem getStockItem() {
            return stockItem;
        }
    }

    static class StockItem {
        @JsonProperty("rprs_mrkt_kor_name")
        private String marketName;
        @JsonProperty("bstp_kor_isnm")
        private String stockName;
        @JsonProperty("stck_prpr")
        private String stockPrice;

        public String getMarketName() {
            return marketName;
        }
        public String getStockName() {return stockName;}
        public String getStockPrice() {
            return stockPrice;
        }
        public void setMarketName(String marketName) {
            this.marketName = marketName;
        }
        public void setStockPrice(String stockPrice) {
            this.stockPrice = stockPrice;
        }
        public StockItem() {}
        public StockItem(String marketName, String stockPrice , String stockName) {
            this.marketName = marketName;
            this.stockPrice = stockPrice;
            this.stockName = stockName;
        }
    }

}