package com.alkemy.challenge.dto;

import com.alkemy.challenge.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class MovieFiltersDto implements Serializable {

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
