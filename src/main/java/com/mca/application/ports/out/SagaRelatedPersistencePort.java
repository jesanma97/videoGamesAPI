package com.mca.application.ports.out;

import java.util.List;

public interface SagaRelatedPersistencePort {
    List<String> getIdsSagasRelatedBySagaId(int sagaId);
}
