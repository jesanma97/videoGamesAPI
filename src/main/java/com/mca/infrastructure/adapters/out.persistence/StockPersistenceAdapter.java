package com.mca.infrastructure.adapters.out.persistence;

import com.google.gson.Gson;
import com.mca.application.ports.out.StockPersistencePort;
import com.mca.infrastructure.adapters.out.persistence.dao.StockDao;
import com.mca.infrastructure.adapters.out.persistence.entities.StockEntity;
import com.mca.infrastructure.model.VideoGameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StockPersistenceAdapter implements StockPersistencePort {
    private static final Logger log = LoggerFactory.getLogger(StockPersistenceAdapter.class);
    private StockDao stockDao;

    @Autowired
    public StockPersistenceAdapter(StockDao stockDao){
        this.stockDao = stockDao;
    }
    @Override
    public void updateStockVideoGame(VideoGameEvent videoGameEvent) {
        try{
            StockEntity stockEntity = new StockEntity();
            stockEntity.setId(Math.toIntExact(videoGameEvent.getStock_id()));
            stockEntity.setAvailability(videoGameEvent.isAvailability());
            stockEntity.setLastUpdated(videoGameEvent.getTime_update());
            stockDao.save(stockEntity);
        }catch (Exception e) {
            Gson gson = new Gson();
            String videoGameEventJSON = gson.toJson(videoGameEvent);
            log.error(String.format("An error occurred while trying to update the stock: %s", videoGameEventJSON), e);
        }
    }
}
