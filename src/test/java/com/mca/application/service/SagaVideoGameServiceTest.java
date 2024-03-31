package com.mca.application.service;

import com.mca.application.services.SagaVideoGameService;
import com.mca.infrastructure.adapters.out.persistence.SagaVideoGamePersistenceAdapter;
import com.mca.infrastructure.adapters.out.persistence.entities.SagaEntity;
import com.mca.infrastructure.model.Saga;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SagaVideoGameServiceTest {
    @Mock
    private SagaVideoGamePersistenceAdapter sagaVideoGamePersistenceAdapter;
    @InjectMocks
    private SagaVideoGameService sagaVideoGameService;

    @BeforeEach
    void setUp(){
        this.sagaVideoGameService = new SagaVideoGameService(sagaVideoGamePersistenceAdapter);
    }

    @Test
    @DisplayName("getListSagasByGameId")
    void getListSagasByGameId(){
        List<Saga> listSagas = new ArrayList<>();
        Saga saga = Saga.builder().id(1).title("Whispers").relevance(10).build();
        listSagas.add(saga);
        ResponseEntity<?> responseTest = ResponseEntity.ok(listSagas);
        doReturn(responseTest).when(this.sagaVideoGamePersistenceAdapter).getListSagasByGameId(Mockito.anyInt());
        ResponseEntity<?> response = sagaVideoGameService.getListSagasByGameId(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Saga> responseBody = (List<Saga>) response.getBody();
        Assertions.assertEquals(listSagas.get(0).getId(), responseBody.get(0).getId());
    }
}
