package com.alkemy.challenge.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CharacterDto{
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String story;
    private List<MovieDto> movies = new ArrayList<>();
}
