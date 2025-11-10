package com.example.fitTrackBackend.Service;

import com.example.fitTrackBackend.Model.UserAccounts;
import com.example.fitTrackBackend.Repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo, EmailService emailService){
        this.userRepo = userRepo;
    }

    public List<UserAccounts>allUsers(){
        List<UserAccounts> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users;
    }
}

