package com.example.fitTrackBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerfifyUserDto {
    private String email;

    private String verificationCode;
}
