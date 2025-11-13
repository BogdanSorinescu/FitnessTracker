package com.example.fitTrackBackend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class WeightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int kiloGrams;

    private LocalDateTime date = LocalDateTime.now(); //saves date of when teh weight was added

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private loggerModel exercise;
}
