package com.example.fitTrackBackend;

import com.example.fitTrackBackend.Model.CaloricItems;
import org.springframework.web.bind.annotation.*;
import com.example.fitTrackBackend.Service.CaloricService;

import java.util.List;

@RestController
@RequestMapping("api/cals")
@CrossOrigin(origins = "*") //allows front-end connectivity
public class Controller {

    private final CaloricService service;

    public Controller(CaloricService service) {

        this.service = service;
    }

    //get mapping that will show us all the calories entered
    @GetMapping
    public List<CaloricItems> getAll() {
        return service.getAllItems();
    }

    @PostMapping
    public CaloricItems addNutrition(@RequestBody CaloricItems item){
        return service.addItem(item);
    }

    @PutMapping("/{id}")
    public CaloricItems updateItems(@PathVariable Long id, @RequestBody CaloricItems updateItem) {
        return service.updateItem(id, updateItem);
    }

    // Delete item
    @DeleteMapping("/{id}")
    public void deleteItems(@PathVariable Long id) {
        service.deleteItem(id);
    }

    // Patch item (partial update)
    @PatchMapping("/{id}")
    public CaloricItems editItem(@PathVariable Long id, @RequestBody CaloricItems updates) {
        return service.patchItem(id, updates);
    }
}


