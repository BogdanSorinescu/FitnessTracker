package com.example.fitTrackBackend.Repo;

import com.example.fitTrackBackend.Model.UserAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserAccounts, Long > {

    Optional<UserAccounts> findByEmail(String email);

    Optional<UserAccounts> findByVerificationCode(String verificationCode);
}

