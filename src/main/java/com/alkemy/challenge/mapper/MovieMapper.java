package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.MovieDto;
import com.alkemy.challenge.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    public MovieDto movieEntity2Dto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        dto.setImage(movie.getImage());
        dto.setCreationDate(movie.getCreationDate());
        dto.setRating(movie.getRating());
        return dto;
    }

    public Movie movieDto2Entity(MovieDto dto) {
        Movie movie = new Movie();
        movie.setName(dto.getName());
        movie.setImage(dto.getImage());
        movie.setCreationDate(dto.getCreationDate());
        movie.setRating(dto.getRating());
        return movie;
    }

    public List<MovieDto> movieEntity2DtoList(List<Movie> movies) {
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            movieDtos.add(movieEntity2Dto(movie));
        }
        return movieDtos;
    }
}