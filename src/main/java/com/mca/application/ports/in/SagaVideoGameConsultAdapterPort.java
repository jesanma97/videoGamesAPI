package com.mca.application.ports.in;

import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.model.SagaVideoGame;

import java.util.List;

public interface SagaVideoGameConsultAdapterPort {
    List<Saga> getListSagasByGameId(int gameId);
}
