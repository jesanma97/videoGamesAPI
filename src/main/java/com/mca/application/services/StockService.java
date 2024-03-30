package com.mca.application.services;

import com.mca.application.ports.out.StockPersistencePort;
import com.mca.infrastructure.adapters.out.persistence.StockPersistenceAdapter;
import com.mca.infrastructure.model.VideoGameEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private StockPersistencePort stockPersistencePort;
    @Autowired
    public StockService(StockPersistenceAdapter stockPersistenceAdapter){
        this.stockPersistencePort = stockPersistenceAdapter;
    }
    public void updateStockVideoGame(VideoGameEvent videoGameEvent) {
        stockPersistencePort.updateStockVideoGame(videoGameEvent);
    }
}
