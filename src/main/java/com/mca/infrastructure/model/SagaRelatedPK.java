package com.mca.infrastructure.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SagaRelatedPK {
    private Saga sagaRelated1;
    private Saga sagaRelated2;
}
