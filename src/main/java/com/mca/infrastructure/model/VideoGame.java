package com.mca.infrastructure.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class VideoGame {
    private int id;
    private String title;
    private String genre;
    private Timestamp releaseDate;
    List<Promotion> promotionList;
    List<Stock> stockList;
}
