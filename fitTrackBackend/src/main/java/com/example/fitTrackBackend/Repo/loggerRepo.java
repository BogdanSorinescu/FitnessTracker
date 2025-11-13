package com.example.fitTrackBackend.Repo;


import com.example.fitTrackBackend.Model.loggerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface loggerRepo extends JpaRepository <loggerModel, Long> {



}
