package com.alkemy.challenge.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Getter
@Setter
public class UserDto implements Serializable {

    @Email(message = "Must be an email")
    private String username;

    private String password;
}
