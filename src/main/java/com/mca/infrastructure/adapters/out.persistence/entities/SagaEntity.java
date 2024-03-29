package com.mca.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SAGA")
public class SagaEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "RELEVANCE", nullable = false)
    private int relevance;
}
