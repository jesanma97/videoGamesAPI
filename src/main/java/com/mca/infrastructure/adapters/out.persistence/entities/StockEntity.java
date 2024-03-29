package com.mca.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STOCK")
public class StockEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "AVAILABILITY")
    private boolean availability;
    @Column(name = "LAST_UPDATED")
    private Timestamp lastUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="VIDEOGAME_ID", nullable = false)
    private VideoGameEntity videoGame;
}
