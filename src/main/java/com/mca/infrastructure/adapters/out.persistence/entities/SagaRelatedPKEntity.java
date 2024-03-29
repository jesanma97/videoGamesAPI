package com.mca.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class SagaRelatedPKEntity implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SAGA_ID_RELATED_1", nullable = false)
    private SagaEntity sagaRelated1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SAGA_ID_RELATED_2", nullable = false)
    private SagaEntity sagaRelated2;
}
