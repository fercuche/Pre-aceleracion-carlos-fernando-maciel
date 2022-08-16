package com.alkemy.challenge.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Getter
@Setter
public class UserDto implements Serializable {
    @Email
    private String username;
    @Size(min = 8)
    private String password;
}
