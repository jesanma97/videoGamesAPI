package com.mca.application.ports.in;

import com.mca.infrastructure.model.VideoGameEvent;
import org.springframework.http.ResponseEntity;

public interface StockConsultPort {
    void updateStockVideoGame(VideoGameEvent videoGameEvent);
}
