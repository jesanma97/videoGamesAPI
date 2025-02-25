package com.mca.infrastructure.adapters.in.web.controller;

import com.mca.application.ports.in.SagaRelatedConsultPort;
import com.mca.application.ports.in.SagaVideoGameConsultAdapterPort;
import com.mca.infrastructure.adapters.in.web.SagaRelatedConsultAdapter;
import com.mca.infrastructure.adapters.in.web.SagaVideoGameConsultAdapter;
import com.mca.infrastructure.commons.Endpoints;
import com.mca.infrastructure.model.ErrorMca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
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
    public ResponseEntity<?> getSagasByGameId(@PathVariable(required = false) Object gameId){
        ResponseEntity<?> response = validationId(gameId);
        if(response.getStatusCode().is2xxSuccessful()){
            if(gameId instanceof String){
                gameId = Integer.parseInt((String) gameId);
            }
            response = sagaVideoGameConsultAdapterPort.getListSagasByGameId((Integer) gameId);
        }
        return response;
    }
    @GetMapping(value = Endpoints.SagaRelated.GET_SAGAS_RELATED_BY_SAGA_ID, produces = "application/json")
    public ResponseEntity<?> getSagasRelatedBySagaId(@PathVariable(required = false) Object sagaId){
        ResponseEntity<?> response = validationId(sagaId);
        if(response.getStatusCode().is2xxSuccessful()){
            if(sagaId instanceof String){
                sagaId = Integer.parseInt((String) sagaId);
            }
            response = sagaRelatedConsultPort.getIdsSagasRelatedBySagaId((Integer) sagaId);
        }
        return response;
    }

    /**
     * Check if id is null or if the type is not valid
     *
     * @param  id
     * @return  If it's null returns BadRequest, if its type is not valid returns
     *                              Not Found, if it isn´t any problem an OK
     */
    private ResponseEntity<?> validationId(Object id){
        if (id == null) {
            return ResponseEntity.badRequest().body(new ErrorMca(HttpStatus.BAD_REQUEST.value(), "ID cannot be null"));
        }

        if (!(id instanceof Integer)) {
            try{
                Integer.parseInt((String) id);
            }catch(ClassCastException | NumberFormatException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMca(HttpStatus.NOT_FOUND.value(), "The format of ID is not correct"));
            }

        }
        return ResponseEntity.ok("");
    }
}
