package com.alkemy.challenge.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    private String username;
    private String password;
}
