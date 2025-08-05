package com.app.campionsl.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "`partidas`")
public class Match {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "juego")
    private String game;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Convert(converter = MatchResultConverter.class)
    @Column(name = "resultado", columnDefinition = "TEXT")
    private MatchResultDTO matchResult;

    @Column(name = "fecha_inicio")
    private LocalDateTime StartTime;
    @Column(name = "fecha_fin")
    private LocalDateTime EndTime;

}
