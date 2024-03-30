package com.mca.infrastructure.adapters.in.web;

import com.mca.application.ports.in.SagaRelatedConsultPort;
import com.mca.application.services.SagaRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SagaRelatedConsultAdapter implements SagaRelatedConsultPort {
    private SagaRelatedService sagaRelatedService;
    @Autowired
    public SagaRelatedConsultAdapter(SagaRelatedService sagaRelatedService){
        this.sagaRelatedService = sagaRelatedService;
    }
    @Override
    public ResponseEntity<?> getIdsSagasRelatedBySagaId(int sagaId) {
        return sagaRelatedService.getIdsSagasRelatedBySagaId(sagaId);
    }
}
