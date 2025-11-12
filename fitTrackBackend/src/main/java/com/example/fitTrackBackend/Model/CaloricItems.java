package com.example.fitTrackBackend.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CaloricItems {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String itemName;
    private Integer calorieCount;
    private Integer caloriesPerDay;
    private Integer ProteinAmount;

}
