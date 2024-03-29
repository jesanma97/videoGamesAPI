package com.mca.application.ports.out;

import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.model.SagaVideoGame;

import java.util.List;

public interface SagaVideoGamePersistencePort {
    List<Saga> getListSagasByGameId(int gameId);
}
