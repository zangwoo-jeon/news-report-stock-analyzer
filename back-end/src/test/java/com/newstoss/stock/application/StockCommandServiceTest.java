package com.newstoss.stock.application;

import com.newstoss.stock.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StockCommandServiceTest {
    @Autowired StockCommandService service;

    @Test
    public void 종목_저장() {
        //given
        Stock stock = Stock.createStock(
                "005930",
                "삼성전자",
                53000,
                "KOSPI",
                null
        );
        //when
        Long saveId = service.save(stock);
        //then
        assertThat(saveId).isNotNull();
    }


}