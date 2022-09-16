package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.CharacterDto;
import com.alkemy.challenge.dto.MovieBasicDto;
import com.alkemy.challenge.dto.MovieDto;
import com.alkemy.challenge.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    public Movie movieDto2Entity(MovieDto dto) {
        Movie movie = new Movie();
        if (dto.getId() != null){
            movie.setId(dto.getId());
        }
        movie.setName(dto.getName());
        movie.setImage(dto.getImage());
        movie.setCreationDate(dto.getCreationDate());
        movie.setRating(dto.getRating());
        movie.setGenreId(dto.getGenreId());
        movie.setCharacters(characterMapper.characterDtoList2EntitySet(dto.getCharacters()));
        return movie;
    }


    public MovieDto movieEntity2Dto(Movie movie, boolean loadCharacters) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        dto.setImage(movie.getImage());
        dto.setCreationDate(movie.getCreationDate());
        dto.setRating(movie.getRating());
        dto.setGenreId(movie.getGenreId());
        if(loadCharacters) {
            List<CharacterDto> characters = characterMapper.characterSetEntity2Dto(movie.getCharacters(), false);
            dto.setCharacters(characters);
        }
        return dto;
    }

    public List<MovieDto> movieEntity2DtoList(List<Movie> movies, boolean loadCharacters) {
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            movieDtos.add(movieEntity2Dto(movie, loadCharacters));
        }
        return movieDtos;
    }

    public MovieBasicDto movieEntity2BasicDto(Movie movie){
        MovieBasicDto dto = new MovieBasicDto();
        dto.setImage(movie.getImage());
        dto.setName(movie.getName());
        dto.setCreationDate(movie.getCreationDate());
        return dto;
    }

    public List<MovieBasicDto> movieEntityList2BasicDto(List<Movie> movies){
        List<MovieBasicDto> dtos = new ArrayList<>();
        for(Movie movie : movies){
            dtos.add(movieEntity2BasicDto(movie));
        }
        return dtos;
    }
}