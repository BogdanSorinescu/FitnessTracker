package com.example.fitTrackBackend.Repo;
import com.example.fitTrackBackend.Model.CaloricItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<CaloricItems, Long> {

}
