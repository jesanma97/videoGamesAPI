package com.mca.application.ports.in;

import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.model.SagaVideoGame;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SagaVideoGameConsultAdapterPort {
    ResponseEntity<?> getListSagasByGameId(int gameId);
}
