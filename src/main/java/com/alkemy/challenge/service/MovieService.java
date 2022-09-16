package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.MovieBasicDto;
import com.alkemy.challenge.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> getAll();
    MovieDto getById(Long id);
    List<MovieBasicDto> getByFilters(String name, String genre, String creationDate, String order);
    MovieDto save(MovieDto dto);
    MovieDto update (Long id, MovieDto dto);
    void delete(Long id);
}
