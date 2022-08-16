package com.alkemy.challenge.service.impl;

import com.alkemy.challenge.entity.Genre;
import com.alkemy.challenge.dto.GenreDto;
import com.alkemy.challenge.mapper.GenreMapper;
import com.alkemy.challenge.repository.GenreRepository;
import com.alkemy.challenge.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;

    public GenreDto save(GenreDto dto){
        Genre genre = genreMapper.genreDto2Entity(dto);
        Genre saved = genreRepository.save(genre);
        GenreDto result = genreMapper.genreEntity2Dto(saved);
        return result;
    }

    public List<GenreDto> getAll() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDto> result = genreMapper.genreEntity2DtoList(genres);
        return result;
    }
}
