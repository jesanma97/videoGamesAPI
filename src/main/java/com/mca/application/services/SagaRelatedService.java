package com.mca.application.services;

import com.mca.application.ports.out.SagaRelatedPersistencePort;
import com.mca.infrastructure.adapters.out.persistence.SagaRelatedPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaRelatedService {
    private SagaRelatedPersistencePort sagaRelatedPersistencePort;
    @Autowired
    public SagaRelatedService(SagaRelatedPersistenceAdapter sagaRelatedPersistenceAdapter){
        this.sagaRelatedPersistencePort = sagaRelatedPersistenceAdapter;
    }
    public ResponseEntity<?> getIdsSagasRelatedBySagaId(int sagaId) {
        return sagaRelatedPersistencePort.getIdsSagasRelatedBySagaId(sagaId);
    }
}
