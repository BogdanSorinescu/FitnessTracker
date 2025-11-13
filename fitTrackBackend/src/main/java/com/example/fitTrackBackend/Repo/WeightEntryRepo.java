package com.example.fitTrackBackend.Repo;

import com.example.fitTrackBackend.Model.WeightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightEntryRepo extends JpaRepository<WeightEntity, Long> {
}
