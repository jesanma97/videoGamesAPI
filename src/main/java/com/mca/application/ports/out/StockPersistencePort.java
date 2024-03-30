package com.mca.application.ports.out;

import com.mca.infrastructure.model.VideoGameEvent;
import org.springframework.http.ResponseEntity;

public interface StockPersistencePort {
    void updateStockVideoGame(VideoGameEvent videoGameEvent);
}
