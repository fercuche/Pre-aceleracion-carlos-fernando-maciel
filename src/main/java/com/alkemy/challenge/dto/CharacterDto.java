package com.alkemy.challenge.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
public class CharacterDto implements Serializable {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String story;
}
