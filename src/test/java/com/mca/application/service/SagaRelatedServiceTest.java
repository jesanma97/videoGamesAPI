package com.mca.application.service;

import com.mca.application.services.SagaRelatedService;
import com.mca.infrastructure.adapters.out.persistence.SagaRelatedPersistenceAdapter;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SagaRelatedServiceTest {
    @Mock
    private SagaRelatedPersistenceAdapter sagaRelatedPersistenceAdapter;
    @InjectMocks
    private SagaRelatedService sagaRelatedService;
    @BeforeEach
    void setUp(){
        this.sagaRelatedService = new SagaRelatedService(sagaRelatedPersistenceAdapter);
    }

    @Test
    @DisplayName("getIdsSagasRelatedBySagaId OK")
    void getIdsSagasRelatedBySagaIdOK(){
        List<String> idsSagas = new ArrayList<>();
        idsSagas.add("3");
        idsSagas.add("1");
        ResponseEntity<?> responseTest = ResponseEntity.ok(idsSagas);
        doReturn(responseTest).when(this.sagaRelatedPersistenceAdapter).getIdsSagasRelatedBySagaId(Mockito.anyInt());
        ResponseEntity<?> responseService = sagaRelatedService.getIdsSagasRelatedBySagaId(1);
        Assertions.assertEquals(HttpStatus.OK, responseService.getStatusCode());
        List<String> responseBody = (List<String>) responseService.getBody();
        List<String> expectedList = Arrays.asList("3", "1");
        Assertions.assertEquals(expectedList, responseBody);
    }

}
