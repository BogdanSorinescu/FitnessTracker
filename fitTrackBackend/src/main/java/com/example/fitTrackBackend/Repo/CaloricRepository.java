package com.example.fitTrackBackend.Repo;

import com.example.fitTrackBackend.Model.CaloricItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaloricRepository extends JpaRepository<CaloricItems, Long> {

    @Query("SELECT p from CaloricItems p WHERE " +
            "LOWER(p.itemName) LIKE LOWER(CONCAT(:keyword, '%'))")
    List<CaloricItems> searchItems(String keyword);
}
