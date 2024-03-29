package com.mca.infrastructure.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SagaVideoGamePK {
    private Saga saga;
    private VideoGame videoGame;
}
