package com.alkemy.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class MovieFiltersDto {

    private String name;
    private String genre;
    private String creationDate;
    private String order;

    public boolean isAsc(){
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDesc(){
        return order.compareToIgnoreCase("DESC") == 0;
    }

}
