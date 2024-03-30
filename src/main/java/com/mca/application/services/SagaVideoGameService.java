package com.mca.application.services;

import com.mca.application.ports.out.SagaVideoGamePersistencePort;
import com.mca.infrastructure.adapters.out.persistence.SagaVideoGamePersistenceAdapter;
import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.model.SagaVideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaVideoGameService {
    private SagaVideoGamePersistencePort sagaVideoGamePersistencePort;

    @Autowired
    public SagaVideoGameService(SagaVideoGamePersistenceAdapter sagaVideoGamePersistenceAdapter){
        this.sagaVideoGamePersistencePort = sagaVideoGamePersistenceAdapter;
    }
    public ResponseEntity<?> getListSagasByGameId(int gameId) {
        return sagaVideoGamePersistencePort.getListSagasByGameId(gameId);
    }
}
