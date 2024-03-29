package com.mca.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SagaVideoGamePKEntity implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SAGA_ID", nullable = false)
    private SagaEntity saga;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="VIDEOGAME_ID", nullable = false)
    private VideoGameEntity videoGame;
}
