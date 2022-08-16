package com.alkemy.challenge.service.impl;

import com.alkemy.challenge.dto.CharacterDto;
import com.alkemy.challenge.dto.CharacterFiltersDto;
import com.alkemy.challenge.entity.Character;
import com.alkemy.challenge.exception.ParamNotFound;
import com.alkemy.challenge.mapper.CharacterMapper;
import com.alkemy.challenge.repository.CharacterRepository;
import com.alkemy.challenge.repository.specifications.CharacterSpecification;
import com.alkemy.challenge.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterSpecification characterSpecification;
    private final CharacterMapper characterMapper;


    public List<CharacterDto> getAll() {
        List<Character> characters= characterRepository.findAll();
        return characterMapper.characterEntity2DtoList(characters);
    }

    public CharacterDto getById(Long id){
        Optional<Character> character = characterRepository.findById(id);
        if(!character.isPresent()){
            throw new ParamNotFound("el id no existe");
        }
        return characterMapper.characterEntity2Dto(character.get());
    }

    public List<CharacterDto> getByFilters(String name, Integer age, Double weight, List<Long> movies) {
        CharacterFiltersDto filtersDto = new CharacterFiltersDto(name, age, weight, movies);
        List<Character> characters = characterRepository.findAll(characterSpecification.getByFilters(filtersDto));
        return characterMapper.characterEntity2DtoList(characters);
    }

    public CharacterDto save(CharacterDto dto) {
        Character character = characterMapper.characterDto2Entity(dto);
        Character saved = characterRepository.save(character);
        return characterMapper.characterEntity2Dto(saved);
    }

    public CharacterDto update(Long id, CharacterDto dto) {
        Optional<Character> characterOpt = characterRepository.findById(id);
        if(!characterOpt.isPresent()){
            throw new ParamNotFound("no se encuentra el id");
        }
        Character character = characterOpt.get();
        characterMapper.characterEntity2Dto(character);
        Character updated = characterMapper.characterDto2Entity(dto);
        Character saved = characterRepository.save(updated);
        return characterMapper.characterEntity2Dto(saved);
    }

    public void delete(Long id) {
        characterRepository.deleteById(id);
    }

}
