package com.mca.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Saga {
    @JsonProperty
    private int id;
    @JsonProperty
    private String title;
    @JsonProperty
    private int relevance;
}
