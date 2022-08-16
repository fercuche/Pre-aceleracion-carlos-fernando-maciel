package com.alkemy.challenge.mapper;

import com.alkemy.challenge.entity.Genre;
import com.alkemy.challenge.dto.GenreDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public Genre genreDto2Entity(GenreDto dto){
        Genre genre = new Genre();
        genre.setImage(dto.getImage());
        genre.setName(dto.getName());
        return genre;
    }

    public GenreDto genreEntity2Dto(Genre genre){
        GenreDto dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setImage(genre.getImage());
        dto.setName(genre.getName());
        return dto;
    }

    public List<GenreDto> genreEntity2DtoList(List<Genre> genres){
        List<GenreDto> genreDtos = new ArrayList<>();
        for (Genre genre:genres) {
            genreDtos.add(genreEntity2Dto(genre));
        }
        return genreDtos;
    }
}
