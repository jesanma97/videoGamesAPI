package com.mca.infrastructure.adapters.in.web;

import com.mca.application.ports.in.SagaVideoGameConsultAdapterPort;
import com.mca.application.services.SagaVideoGameService;
import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.model.SagaVideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaVideoGameConsultAdapter implements SagaVideoGameConsultAdapterPort {

    private SagaVideoGameService sagaVideoGameService;

    @Autowired
    public SagaVideoGameConsultAdapter(SagaVideoGameService sagaVideoGameService){
        this.sagaVideoGameService = sagaVideoGameService;
    }
    @Override
    public List<Saga> getListSagasByGameId(int gameId) {
        return sagaVideoGameService.getListSagasByGameId(gameId);
    }
}
