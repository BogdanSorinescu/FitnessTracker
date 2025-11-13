package com.example.fitTrackBackend.controller;

import com.example.fitTrackBackend.Model.WeightEntity;
import com.example.fitTrackBackend.Model.loggerModel;
import com.example.fitTrackBackend.Service.loggerService;
import com.example.fitTrackBackend.dto.WeightRequest;
import jakarta.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loggs")
@CrossOrigin(origins = "*") //allows front-end connectivity
public class loggerController {

    private final loggerService loggerService;


    public loggerController(loggerService loggerService) {
        this.loggerService = loggerService;
    }

    @GetMapping
    public List<loggerModel> getAll(){
        return loggerService.getAllExercises();
    }

    @PostMapping("/addExercise")
    public loggerModel addExercises(@RequestBody loggerModel item){
        return loggerService.addExercise(item);

    }

    @PatchMapping("/{id}/addWeight")
    public WeightEntity addWeight(
            @PathVariable Long id,
            @RequestBody WeightRequest request
            ){
        return loggerService.addWeight(id, request.getKiloGrams());
    }
}
