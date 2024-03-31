package com.mca.infrastructure.out.persistence;

import com.mca.infrastructure.adapters.out.persistence.SagaRelatedPersistenceAdapter;
import com.mca.infrastructure.adapters.out.persistence.dao.SagaRelatedDao;
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
public class SagaRelatedPersistenceAdapterTest {
    @Mock
    private SagaRelatedDao sagaRelatedDao;
    @InjectMocks
    private SagaRelatedPersistenceAdapter sagaRelatedPersistenceAdapter;

    @BeforeEach
    void setUp(){
        this.sagaRelatedPersistenceAdapter = new SagaRelatedPersistenceAdapter(sagaRelatedDao);
    }

    @Test
    @DisplayName("getIdsSagasRelatedBySagaId KO")
    void getIdsSagasRelatedBySagaIdKO(){
        when(this.sagaRelatedDao.getListIdsSagasRelatedBySagaId(Mockito.anyInt())).thenThrow(NullPointerException.class);
        ResponseEntity<?> response = sagaRelatedPersistenceAdapter.getIdsSagasRelatedBySagaId(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    @DisplayName("getIdsSagasRelatedBySagaId No Results")
    void getIdsSagasRelatedBySagaIdNoResult(){
        List<Object[]> listObjects = new ArrayList<>();
        when(this.sagaRelatedDao.getListIdsSagasRelatedBySagaId(Mockito.anyInt())).thenReturn(listObjects);
        ResponseEntity<?> response = sagaRelatedPersistenceAdapter.getIdsSagasRelatedBySagaId(1);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    @DisplayName("getIdsSagasRelatedBySagaId OK")
    void getIdsSagasRelatedBySagaIdOK(){
        List<Object[]> listObjects = new ArrayList<>();
        listObjects.add(new Object[]{1, 1});
        listObjects.add(new Object[]{3, 2});
        when(this.sagaRelatedDao.getListIdsSagasRelatedBySagaId(Mockito.anyInt())).thenReturn(listObjects);
        ResponseEntity<?> response = sagaRelatedPersistenceAdapter.getIdsSagasRelatedBySagaId(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<String> responseBody = (List<String>) response.getBody();
        List<String> expectedList = Arrays.asList("3", "1");
        Assertions.assertEquals(expectedList, responseBody);
    }
}
