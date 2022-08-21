package com.alkemy.challenge.service;

import com.alkemy.challenge.dto.CharacterBasicDto;
import com.alkemy.challenge.dto.CharacterDto;

import java.util.List;

public interface CharacterService {

    //List<CharacterDto> getAll();
    CharacterDto getById(Long id);
    List<CharacterBasicDto> getByFilters(String name, String age, String weight, List<Long> movies, String order);
    CharacterDto save(CharacterDto dto);
    CharacterDto update (Long id, CharacterDto dto);
    void delete(Long id);


}
