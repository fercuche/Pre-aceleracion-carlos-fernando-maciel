package com.alkemy.challenge.dto; 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CharacterFiltersDto{

    private String name;
    private String age;
    private String weight;
    private List<Long> movies;
    private String order;

    public boolean isAsc(){
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDesc(){
        return order.compareToIgnoreCase("DESC") == 0;
    }

}

