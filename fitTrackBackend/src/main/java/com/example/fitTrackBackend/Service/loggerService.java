package com.example.fitTrackBackend.Service;


import com.example.fitTrackBackend.Model.WeightEntity;
import com.example.fitTrackBackend.Model.loggerModel;
import com.example.fitTrackBackend.Repo.WeightEntryRepo;
import com.example.fitTrackBackend.Repo.loggerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class loggerService {

    private final loggerRepo repo;

    private final WeightEntryRepo weightEntryRepo;
    

    public loggerService(loggerRepo repo, WeightEntryRepo weightEntryRepo) {
        this.repo = repo;
        this.weightEntryRepo = weightEntryRepo;
    }

    public List<loggerModel> getAllExercises() {
        return repo.findAll();
    }
    
    public loggerModel addExercise(loggerModel item){
         return repo.save(item);
    }

    public WeightEntity addWeight(Long exerciseId, int kiloGrams){
        loggerModel exercise = repo.findById(exerciseId)
                .orElseThrow(()-> new RuntimeException("Exercise not found"));

        WeightEntity entity = new WeightEntity();
        entity.setExercise(exercise);
        entity.setKiloGrams(kiloGrams);

        return weightEntryRepo.save(entity);
    }


}
