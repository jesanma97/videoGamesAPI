package com.mca.infrastructure.in.web;

import com.mca.application.services.StockService;
import com.mca.infrastructure.adapters.in.web.StockConsultAdapter;
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
public class StockConsultAdapterTest {
    @Mock
    private StockService stockService;
    @InjectMocks
    private StockConsultAdapter stockConsultAdapter;
    VideoGameEvent videoGameEvent = new VideoGameEvent();

    @BeforeEach
    void setUp(){
        this.stockConsultAdapter = new StockConsultAdapter(stockService);
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        videoGameEvent = new VideoGameEvent(1L, true, timestamp);
    }

    @Test
    @DisplayName("updateStockVideoGame")
    void updateStockVideoGame(){
        doNothing().when(this.stockService).updateStockVideoGame(Mockito.any());
        stockConsultAdapter.updateStockVideoGame(videoGameEvent);
    }
}
