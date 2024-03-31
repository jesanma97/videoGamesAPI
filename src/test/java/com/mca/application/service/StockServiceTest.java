package com.mca.application.service;

import com.mca.application.services.StockService;
import com.mca.infrastructure.adapters.out.persistence.StockPersistenceAdapter;
import com.mca.infrastructure.model.VideoGameEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {
    @Mock
    private StockPersistenceAdapter stockPersistenceAdapter;
    @InjectMocks
    private StockService stockService;
    VideoGameEvent videoGameEvent = new VideoGameEvent();

    @BeforeEach
    void setUp(){
        this.stockService = new StockService(stockPersistenceAdapter);
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        videoGameEvent = new VideoGameEvent(1L, true, timestamp);
    }

    @Test
    @DisplayName("updateStockVideoGame")
    void updateStockVideoGame(){
        doNothing().when(this.stockPersistenceAdapter).updateStockVideoGame(Mockito.any());
        stockService.updateStockVideoGame(videoGameEvent);
    }
}
