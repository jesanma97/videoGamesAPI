package com.mca.infrastructure.adapters.out.persistence;

import com.mca.application.ports.out.SagaVideoGamePersistencePort;
import com.mca.infrastructure.adapters.out.persistence.dao.SagaVideoGameDao;
import com.mca.infrastructure.adapters.out.persistence.entities.SagaEntity;
import com.mca.infrastructure.adapters.out.persistence.entities.SagaVideoGameEntity;
import com.mca.infrastructure.adapters.out.persistence.mappers.SagaEntityMapper;
import com.mca.infrastructure.adapters.out.persistence.mappers.SagaVideoGameEntityMapper;
import com.mca.infrastructure.model.ErrorMca;
import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.model.SagaVideoGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SagaVideoGamePersistenceAdapter implements SagaVideoGamePersistencePort {
    private static final Logger log = LoggerFactory.getLogger(SagaVideoGamePersistenceAdapter.class);
    private SagaVideoGameDao sagaVideoGameDao;

    @Autowired
    public SagaVideoGamePersistenceAdapter(SagaVideoGameDao sagaVideoGameDao){
        this.sagaVideoGameDao = sagaVideoGameDao;
    }

    @Override
    public ResponseEntity<?> getListSagasByGameId(int gameId) {
        List<Saga> sagaList;
        try{
            List<SagaEntity> sagaEntityList = sagaVideoGameDao.getListSagasByGameId(gameId);
            if(sagaEntityList.size() == 0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMca(HttpStatus.NOT_FOUND.value(), "No saga found"));
            }
            sagaList = SagaEntityMapper.INSTANCE.sagaEntityListToSagaList(sagaEntityList);
        }catch (Exception e){
            String error = String.format("An error occurred while trying to retrieve the sagas from the game ID: %d", gameId);
            log.error(error, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
        return ResponseEntity.ok(sagaList);
    }
}
