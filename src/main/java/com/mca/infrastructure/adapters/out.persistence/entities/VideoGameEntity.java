package com.mca.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VIDEOGAME")
public class VideoGameEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "GENRE", nullable = false)
    private String genre;
    @Column(name = "RELEASE_DATE", nullable = false)
    private Timestamp releaseDate;
}
