package com.mca.infrastructure.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Saga {
    private int id;
    private String title;
    private int relevance;
}
