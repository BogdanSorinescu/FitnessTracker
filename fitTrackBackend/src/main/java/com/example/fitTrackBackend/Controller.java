package com.example.fitTrackBackend;

import com.example.fitTrackBackend.Model.CaloricItems;
import com.example.fitTrackBackend.Repo.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cals")
@CrossOrigin(origins = "*") //allows front-end connectivity
public class Controller {

    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    //get mapping that will show us all the calories entered
    @GetMapping
    public List<CaloricItems>getAll(){
        return repository.findAll();
    }

    @PostMapping
    public CaloricItems addNutrition(@RequestBody CaloricItems item){
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public CaloricItems updateItems(@PathVariable Long id, @RequestBody CaloricItems updateItem){
        CaloricItems item = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("Could not find item with Id: " + id));

        item.setItemName(updateItem.getItemName());
        item.setCalorieCount(updateItem.getCalorieCount());
        item.setCaloriesPerDay(updateItem.getCaloriesPerDay());

        return repository.save(item);
    }

    @DeleteMapping("/{id]")
    public void deleteItems(@PathVariable Long id){
        repository.deleteById(id);
    }
}


