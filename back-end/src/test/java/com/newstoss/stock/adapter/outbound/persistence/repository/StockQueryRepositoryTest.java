package com.newstoss.stock.adapter.outbound.persistence.repository;

import com.newstoss.stock.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StockQueryRepositoryTest {
    @Autowired private StockRepository stockRepository;
    @Test
    public void 종목_찾기() {
        //given
        Stock stock = Stock.createStock(
                "005930",
                "삼성전자",
                53000,
                "KOSPI",
                null
        );
        Long saveId = stockRepository.save(stock).getId();
        //when
        Stock findStock = stockRepository.findById(saveId)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found"));
        //then
        assertThat(findStock).isEqualTo(stock);
    }
}