package com.mca.infrastructure.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Promotion {
    private int id;
    private Timestamp validFrom;
    private int price;
    private VideoGame videoGame;
}
