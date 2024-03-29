package com.mca.infrastructure.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Stock {
    private int id;
    private boolean availability;
    private Timestamp lastUpdated;
    private VideoGame videoGame;
}
