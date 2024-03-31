package com.mca.infrastructure.out.persistence;

import com.mca.infrastructure.adapters.out.persistence.SagaVideoGamePersistenceAdapter;
import com.mca.infrastructure.adapters.out.persistence.dao.SagaVideoGameDao;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SagaVideoGamePersistenceAdapterTest {
    @Mock
    private SagaVideoGameDao sagaVideoGameDao;
    @InjectMocks
    private SagaVideoGamePersistenceAdapter sagaVideoGamePersistenceAdapter;

    @BeforeEach
    void setUp(){
        this.sagaVideoGamePersistenceAdapter = new SagaVideoGamePersistenceAdapter(sagaVideoGameDao);
    }
    @Test
    @DisplayName("getListSagasByGameId KO")
    void getListSagasByGameIdKO(){
        when(this.sagaVideoGameDao.getListSagasByGameId(Mockito.anyInt())).thenThrow(NullPointerException.class);
        ResponseEntity<?> response = sagaVideoGamePersistenceAdapter.getListSagasByGameId(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    @DisplayName("getListSagasByGameId No Results")
    void getListSagasByGameIdNoResults(){
        List<SagaEntity> listObjects = new ArrayList<>();
        when(this.sagaVideoGameDao.getListSagasByGameId(Mockito.anyInt())).thenReturn(listObjects);
        ResponseEntity<?> response = sagaVideoGamePersistenceAdapter.getListSagasByGameId(1);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    @DisplayName("getListSagasByGameId OK")
    void getListSagasByGameIdOK(){
        List<SagaEntity> listSagas = new ArrayList<>();
        SagaEntity sagaEntity = SagaEntity.builder().id(1).title("Whispers").relevance(10).build();
        listSagas.add(sagaEntity);
        when(this.sagaVideoGameDao.getListSagasByGameId(Mockito.anyInt())).thenReturn(listSagas);
        ResponseEntity<?> response = sagaVideoGamePersistenceAdapter.getListSagasByGameId(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Saga> responseBody = (List<Saga>) response.getBody();
        Assertions.assertEquals(listSagas.get(0).getId(), responseBody.get(0).getId());
    }
}
