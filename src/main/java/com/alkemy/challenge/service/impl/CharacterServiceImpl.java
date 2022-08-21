package com.alkemy.challenge.service.impl;

import com.alkemy.challenge.dto.CharacterBasicDto;
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
import org.springframework.transaction.annotation.Transactional;

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
        return characterMapper.characterEntity2DtoList(characters,true);
    }

    @Transactional(readOnly = true)
    public CharacterDto getById(Long id){
        Optional<Character> character = characterRepository.findById(id);
        if(!character.isPresent()){
            throw new ParamNotFound("el id no existe");
        }
        return characterMapper.characterEntity2Dto(character.get(),true);
    }

    @Transactional(readOnly = true)
    public List<CharacterBasicDto> getByFilters(String name, String age, String weight, List<Long> movies, String order) {
        CharacterFiltersDto filtersDto = new CharacterFiltersDto(name, age, weight, movies, order);
        List<Character> characters = characterRepository.findAll(characterSpecification.getByFilters(filtersDto));
        return characterMapper.characterEntityList2BasicDto(characters);
    }

    @Transactional
    public CharacterDto save(CharacterDto dto) throws ParamNotFound {
        Character character = characterMapper.characterDto2Entity(dto);
        Character saved = characterRepository.save(character);
        return characterMapper.characterEntity2Dto(saved,true);
    }

    @Transactional
    public CharacterDto update(Long id, CharacterDto dto) throws ParamNotFound{
        Optional<Character> result = characterRepository.findById(dto.getId());
        if(!result.isPresent()){
            throw new ParamNotFound("no se encuentra el id");
        }
        Character character = characterMapper.characterDto2Entity(dto);
        Character saved = characterRepository.save(character);
        return characterMapper.characterEntity2Dto(saved, true);
    }

    @Transactional
    public void delete(Long id) throws ParamNotFound {
        Optional<Character> result = characterRepository.findById(id);
        if(!result.isPresent()){
            throw new ParamNotFound(("no se encuentra el id"));
        }
        characterRepository.deleteById(id);
    }

}
