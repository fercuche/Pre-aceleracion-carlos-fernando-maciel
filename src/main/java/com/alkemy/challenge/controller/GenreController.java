package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.GenreDto;
import com.alkemy.challenge.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAll(){
        List<GenreDto> genres = genreService.getAll();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping
    public ResponseEntity<GenreDto> save(@RequestBody GenreDto dto){
        GenreDto saved = genreService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }




}
