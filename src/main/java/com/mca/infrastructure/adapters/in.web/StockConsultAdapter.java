package com.mca.infrastructure.adapters.in.web;

import com.mca.application.ports.in.StockConsultPort;
import com.mca.application.services.StockService;
import com.mca.infrastructure.model.VideoGameEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StockConsultAdapter implements StockConsultPort {
    private StockService stockService;
    @Autowired
    public StockConsultAdapter(StockService stockService){
        this.stockService = stockService;
    }
    @Override
    public void updateStockVideoGame(VideoGameEvent videoGameEvent) {
        stockService.updateStockVideoGame(videoGameEvent);
    }
}
