package com.alkemy.challenge.dto; 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CharacterFiltersDto implements Serializable {

    private String name;
    private Integer age;
    private Double weight;
    private List<Long> movies;

}

