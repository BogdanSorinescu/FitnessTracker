package com.example.fitTrackBackend.Service;

import com.example.fitTrackBackend.Model.CaloricItems;
import com.example.fitTrackBackend.Model.loggerModel;
import com.example.fitTrackBackend.Repo.loggerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class loggerService {

    private final loggerRepo repo;
    

    public loggerService(loggerRepo repo) {
        this.repo = repo;
      
    }

    public List<loggerModel> getAllExercises() {
        return repo.findAll();
    }
    
    public loggerModel addExercise(loggerModel item){
         return repo.save(item);
    }

    public loggerModel addKg(loggerModel item){
        return repo.save(item);
    }





}
