package com.alkemy.challenge.service.impl;

import com.alkemy.challenge.dto.MovieDto;
import com.alkemy.challenge.dto.MovieFiltersDto;
import com.alkemy.challenge.entity.Genre;
import com.alkemy.challenge.entity.Movie;
import com.alkemy.challenge.exception.ParamNotFound;
import com.alkemy.challenge.mapper.MovieMapper;
import com.alkemy.challenge.repository.MovieRepository;
import com.alkemy.challenge.repository.specifications.MovieSpecification;
import com.alkemy.challenge.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieSpecification movieSpecification;
    private final MovieMapper movieMapper;

    public List<MovieDto> getAll() {
        List<Movie> movies= movieRepository.findAll();
        return movieMapper.movieEntity2DtoList(movies);
    }

    public MovieDto getById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(!movie.isPresent()){
            throw new ParamNotFound("el id no existe");
        }
        return movieMapper.movieEntity2Dto(movie.get());
    }

    public List<MovieDto> getByFilters(String name, String genre, String creationDate, String order) {
        MovieFiltersDto filtersDto = new MovieFiltersDto(name, genre, creationDate, order);
        List<Movie> movies = movieRepository.findAll(movieSpecification.getByFilters(filtersDto));
        return movieMapper.movieEntity2DtoList(movies);
    }

    public MovieDto save(MovieDto dto) {
        Movie movie = movieMapper.movieDto2Entity(dto);
        Movie saved = movieRepository.save(movie);
        return movieMapper.movieEntity2Dto(saved);
    }

    public MovieDto update(Long id, MovieDto dto) {
        Optional<Movie> movieOpt = movieRepository.findById(id);
        if(!movieOpt.isPresent()){
            throw new ParamNotFound("no se encuentra el id");
        }
        Movie movie = movieOpt.get();
        movieMapper.movieEntity2Dto(movie);
        Movie updated = movieMapper.movieDto2Entity(dto);
        Movie saved = movieRepository.save(updated);
        return movieMapper.movieEntity2Dto(saved);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);

    }
}
