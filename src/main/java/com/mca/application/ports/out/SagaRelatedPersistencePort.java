package com.mca.application.ports.out;

import org.springframework.http.ResponseEntity;

public interface SagaRelatedPersistencePort {
    ResponseEntity<?> getIdsSagasRelatedBySagaId(int sagaId);
}
