package com.example.fitTrackBackend.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class UserAccounts {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private Boolean enabled;
    private String verificationCode;
    private Timestamp verificationExpired;
}
