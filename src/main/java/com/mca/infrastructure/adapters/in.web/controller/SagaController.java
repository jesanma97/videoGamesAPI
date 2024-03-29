package com.mca.infrastructure.adapters.in.web.controller;

import com.mca.application.ports.in.SagaRelatedConsultPort;
import com.mca.application.ports.in.SagaVideoGameConsultAdapterPort;
import com.mca.infrastructure.adapters.in.web.SagaRelatedConsultAdapter;
import com.mca.infrastructure.adapters.in.web.SagaVideoGameConsultAdapter;
import com.mca.infrastructure.commons.Endpoints;
import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.model.SagaVideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SagaController {

    private SagaVideoGameConsultAdapterPort sagaVideoGameConsultAdapterPort;
    private SagaRelatedConsultPort sagaRelatedConsultPort;

    @Autowired
    public SagaController(SagaVideoGameConsultAdapter sagaVideoGameConsultAdapter,
                          SagaRelatedConsultAdapter sagaRelatedConsultAdapter){
        this.sagaVideoGameConsultAdapterPort = sagaVideoGameConsultAdapter;
        this.sagaRelatedConsultPort = sagaRelatedConsultAdapter;
    }

    @GetMapping(value = Endpoints.SagaVideoGame.GET_SAGAS_BY_GAME_ID, produces = "application/json")
    public List<Saga> getSagasByGameId(@PathVariable int gameId){
        return sagaVideoGameConsultAdapterPort.getListSagasByGameId(gameId);
    }
    @GetMapping(value = Endpoints.SagaRelated.GET_SAGAS_RELATED_BY_SAGA_ID, produces = "application/json")
    public List<String> getSagasRelatedBySagaId(@PathVariable int sagaId){
        return sagaRelatedConsultPort.getIdsSagasRelatedBySagaId(sagaId);
    }
}
