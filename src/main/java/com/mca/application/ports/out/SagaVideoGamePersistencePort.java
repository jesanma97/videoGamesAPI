package com.mca.application.ports.out;

import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.model.SagaVideoGame;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SagaVideoGamePersistencePort {
    ResponseEntity<?> getListSagasByGameId(int gameId);
}
