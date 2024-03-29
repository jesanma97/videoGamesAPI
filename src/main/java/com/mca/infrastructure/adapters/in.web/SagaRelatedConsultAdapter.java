package com.mca.infrastructure.adapters.in.web;

import com.mca.application.ports.in.SagaRelatedConsultPort;
import com.mca.application.services.SagaRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaRelatedConsultAdapter implements SagaRelatedConsultPort {
    private SagaRelatedService sagaRelatedService;
    @Autowired
    public SagaRelatedConsultAdapter(SagaRelatedService sagaRelatedService){
        this.sagaRelatedService = sagaRelatedService;
    }
    @Override
    public List<String> getIdsSagasRelatedBySagaId(int sagaId) {
        return sagaRelatedService.getIdsSagasRelatedBySagaId(sagaId);
    }
}
