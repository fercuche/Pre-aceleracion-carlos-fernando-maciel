package com.alkemy.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieBasicDto{

    private String name;
    private String image;
    private LocalDate creationDate;

}
