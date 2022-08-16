package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.CharacterDto;

import java.util.List;

public interface CharacterService {

    List<CharacterDto> getAll();
    CharacterDto getById(Long id);
    List<CharacterDto> getByFilters(String name, Integer age, Double weight, List<Long> movies);
    CharacterDto save(CharacterDto dto);
    CharacterDto update (Long id, CharacterDto dto);
    void delete(Long id);


}
