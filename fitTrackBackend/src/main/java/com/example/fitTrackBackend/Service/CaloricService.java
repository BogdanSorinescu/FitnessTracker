package com.example.fitTrackBackend.Service;
import com.example.fitTrackBackend.Model.CaloricItems;
import com.example.fitTrackBackend.Repo.CaloricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaloricService {

    private final CaloricRepository repository;

    public CaloricService(CaloricRepository repository){
        this.repository= repository;
    }

    public List<CaloricItems> getAllItems() {
        return repository.findAll();
    }

    public CaloricItems addItem(CaloricItems item){
        return repository.save(item);
    }

    public CaloricItems updateItem(Long id, CaloricItems updateItem){
        CaloricItems item = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("Could not find item with Id: " + id));

        item.setItemName(updateItem.getItemName());
        item.setCalorieCount(updateItem.getCalorieCount());
        item.setCaloriesPerDay(updateItem.getCaloriesPerDay());

        return repository.save(item);
    }

    public CaloricItems patchItem(Long id, CaloricItems updates){
        CaloricItems item = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Item not found with id: "+ id));

        if(updates.getItemName()!=null){
            item.setItemName(updates.getItemName());
        }

        if(updates.getCalorieCount() != null){
            item.setCalorieCount(updates.getCalorieCount());
        }

        if(updates.getCaloriesPerDay() != null){
            item.setCaloriesPerDay(updates.getCaloriesPerDay());
        }

       return repository.save(item);
    }

    public void deleteItem(Long id){
        repository.deleteById(id);
    }
}
