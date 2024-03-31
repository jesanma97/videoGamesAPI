package com.mca.infrastructure.in.web;

import com.mca.application.services.SagaVideoGameService;
import com.mca.infrastructure.adapters.in.web.SagaVideoGameConsultAdapter;
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

@ExtendWith(MockitoExtension.class)
public class SagaVideoGameConsultAdapterTest {
    @Mock
    private SagaVideoGameService sagaVideoGameService;
    @InjectMocks
    private SagaVideoGameConsultAdapter sagaVideoGameConsultAdapter;

    @BeforeEach
    void setUp(){
        this.sagaVideoGameConsultAdapter = new SagaVideoGameConsultAdapter(sagaVideoGameService);
    }

    @Test
    @DisplayName("getListSagasByGameId")
    void getListSagasByGameId(){
        List<Saga> listSagas = new ArrayList<>();
        Saga saga = Saga.builder().id(1).title("Whispers").relevance(10).build();
        listSagas.add(saga);
        ResponseEntity<?> responseTest = ResponseEntity.ok(listSagas);
        doReturn(responseTest).when(this.sagaVideoGameService).getListSagasByGameId(Mockito.anyInt());
        ResponseEntity<?> response = sagaVideoGameConsultAdapter.getListSagasByGameId(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Saga> responseBody = (List<Saga>) response.getBody();
        Assertions.assertEquals(listSagas.get(0).getId(), responseBody.get(0).getId());
    }
}
