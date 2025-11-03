package com.example.fitTrackBackend.Model;
import jakarta.persistence.*;
import lombok.Setter;

@Entity
public class CaloricItems {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String itemName;
    private int calorieCount;
    private int caloriesPerDay;

    private Long getId(){
        return Id;
    }

    private String getItemName(){
        return itemName;
    }

    private int getCalorieCount(){
        return calorieCount;
    }

    private int getCaloriesPerDay(){
        return caloriesPerDay;
    }
}
