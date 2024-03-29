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
@Table(name = "PROMOTION")
public class PromotionEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "VALID_FROM")
    private Timestamp validFrom;
    @Column(name = "PRICE")
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="VIDEOGAME_ID", nullable = false)
    private VideoGameEntity videoGame;

}
