package com.mca.application.ports.in;

import java.util.List;

public interface SagaRelatedConsultPort {
    List<String> getIdsSagasRelatedBySagaId(int sagaId);
}
