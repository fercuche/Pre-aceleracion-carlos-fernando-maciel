package com.alkemy.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public class MovieDto implements Serializable {
    private Long id;
    private String name;
    private String image;
    private LocalDate creationDate;
    private Integer rating;

}
