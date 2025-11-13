package com.example.fitTrackBackend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class loggerModel {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String exerciseName;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<WeightEntity> weightEntities;


}
