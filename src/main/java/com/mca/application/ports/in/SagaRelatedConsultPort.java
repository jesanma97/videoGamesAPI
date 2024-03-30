package com.mca.application.ports.in;

import org.springframework.http.ResponseEntity;

public interface SagaRelatedConsultPort {
    ResponseEntity<?> getIdsSagasRelatedBySagaId(int sagaId);
}
