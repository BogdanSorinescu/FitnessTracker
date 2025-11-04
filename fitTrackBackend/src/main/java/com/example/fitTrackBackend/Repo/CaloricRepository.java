package com.example.fitTrackBackend.Repo;

import com.example.fitTrackBackend.Model.CaloricItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaloricRepository extends JpaRepository<CaloricItems, Long> {

}
