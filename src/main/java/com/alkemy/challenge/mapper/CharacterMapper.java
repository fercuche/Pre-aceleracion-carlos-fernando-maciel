package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.CharacterDto;
import com.alkemy.challenge.entity.Character;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    public CharacterDto characterEntity2Dto(Character character){
        CharacterDto dto = new CharacterDto();
        dto.setId(character.getId());
        dto.setImage(character.getImage());
        dto.setName(character.getName());
        dto.setAge(character.getAge());
        dto.setWeight(character.getWeight());
        dto.setStory(character.getStory());
        return dto;
    }

    public Character characterDto2Entity(CharacterDto dto){
        Character character = new Character();
        character.setImage(dto.getImage());
        character.setName(dto.getName());
        character.setAge(dto.getAge());
        character.setWeight(dto.getWeight());
        character.setStory(dto.getStory());
        return character;
    }

    public List<CharacterDto> characterEntity2DtoList(List<Character> characters) {
        List<CharacterDto> characterDtos = new ArrayList<>();
        for (Character character:characters) {
            characterDtos.add(characterEntity2Dto(character));
        }
        return characterDtos;
    }
}
