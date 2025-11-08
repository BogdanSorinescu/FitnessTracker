package com.example.fitTrackBackend;

import com.example.fitTrackBackend.Model.CaloricItems;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.fitTrackBackend.Service.CaloricService;

import java.util.List;

@RestController
@RequestMapping("api/cals")
@CrossOrigin(origins = "*") //allows front-end connectivity
public class Controller {

    ;
    private final CaloricService caloricService;

    public Controller(CaloricService service, CaloricService caloricService) {


        this.caloricService = caloricService;
    }

    //get mapping that will show us all the calories entered
    @GetMapping
    public List<CaloricItems> getAll() {
        return caloricService.getAllItems();
    }

    @PostMapping
    public CaloricItems addNutrition(@RequestBody CaloricItems item){
        return caloricService.addItem(item);
    }

    @PutMapping("/{id}")
    public CaloricItems updateItems(@PathVariable Long id, @RequestBody CaloricItems updateItem) {
        return caloricService.updateItem(id, updateItem);
    }

    // Delete item
    @DeleteMapping("/{id}")
    public void deleteItems(@PathVariable Long id) {
        caloricService.deleteItem(id);
    }

    // Patch item (partial update)
    @PatchMapping("/{id}")
    public CaloricItems editItem(@PathVariable Long id, @RequestBody CaloricItems updates) {
        return caloricService.patchItem(id, updates);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CaloricItems>> searchItems(@RequestParam String keyword){
        System.out.println("Searching for items with " + keyword);
        List<CaloricItems>searchItems = caloricService.searchItems(keyword);
        return new ResponseEntity<>(searchItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaloricItems> selectSearchItem(@PathVariable Long id){
        CaloricItems item = caloricService.selectSearchItem(id);
        if (item!=null){
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}


