package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.GenreDto;

import java.util.List;

public interface GenreService {

    GenreDto save(GenreDto dto);
    List<GenreDto> getAll();
}
