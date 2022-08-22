package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.CharacterBasicDto;
import com.alkemy.challenge.dto.CharacterDto;
import com.alkemy.challenge.dto.MovieDto;
import com.alkemy.challenge.entity.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CharacterMapper {


    private MovieMapper movieMapper = new MovieMapper();

    public CharacterDto characterEntity2Dto(Character character, boolean loadMovies){
        CharacterDto dto = new CharacterDto();
        dto.setId(character.getId());
        dto.setImage(character.getImage());
        dto.setName(character.getName());
        dto.setAge(character.getAge());
        dto.setWeight(character.getWeight());
        dto.setStory(character.getStory());
        if(loadMovies) {
            List<MovieDto> movies = movieMapper.movieEntity2DtoList(character.getMovies(), false);
            dto.setMovies(movies);
        }
        return dto;
    }

    public Character characterDto2Entity(CharacterDto dto){
            Character character = new Character();
            if (dto.getId() != null){
                character.setId(dto.getId());
            }
            character.setImage(dto.getImage());
            character.setName(dto.getName());
            character.setAge(dto.getAge());
            character.setWeight(dto.getWeight());
            character.setStory(dto.getStory());
            return character;
    }

    public List<CharacterDto> characterEntity2DtoList(List<Character> characters, boolean loadMovies) {
        List<CharacterDto> characterDtos = new ArrayList<>();
        for (Character character:characters) {
            characterDtos.add(characterEntity2Dto(character, loadMovies));
        }
        return characterDtos;
    }

    public List<CharacterDto> characterSetEntity2Dto(Set<Character> characters, boolean loadMovies){
        List<CharacterDto> dtos = new ArrayList<>();
        for(Character character : characters){
            dtos.add(characterEntity2Dto(character,loadMovies));
        }
        return dtos;
    }

    public Set<Character> characterDtoList2EntitySet(List<CharacterDto> dtos) {
        Set<Character> characters = new HashSet<>();
        for(CharacterDto dto : dtos){
            characters.add(characterDto2Entity(dto));
        }
        return characters;
    }

    public CharacterBasicDto characterEntity2BasicDto(Character character){
        CharacterBasicDto basicDto = new CharacterBasicDto();
        basicDto.setImage(character.getImage());
        basicDto.setName(character.getName());
        return basicDto;
    }

    public List<CharacterBasicDto> characterEntityList2BasicDto(List<Character> characters){
        List<CharacterBasicDto> basicDtos = new ArrayList<>();
        for(Character character : characters){
            basicDtos.add(characterEntity2BasicDto(character));
        }
        return basicDtos;
    }
}
