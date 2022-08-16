package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.MovieDto;
import com.alkemy.challenge.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAll(){
        List<MovieDto> movies = movieService.getAll();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable Long id){
        MovieDto dto = movieService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String creationDate,
            @RequestParam(required = false) String order
    ){
        List<MovieDto> movieDtos = movieService.getByFilters(name, genre, creationDate, order);
        return ResponseEntity.ok(movieDtos);
    }

    @PostMapping
    public ResponseEntity<MovieDto> save(@RequestBody MovieDto dto){
        MovieDto result = movieService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable Long id, @RequestBody MovieDto dto) {
        MovieDto result = movieService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}