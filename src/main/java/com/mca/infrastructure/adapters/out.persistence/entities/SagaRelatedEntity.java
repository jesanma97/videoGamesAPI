package com.mca.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SAGA_RELATED")
public class SagaRelatedEntity {
    @EmbeddedId
    private SagaRelatedPKEntity sagaRelatedPK;
}
