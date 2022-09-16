package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.CharacterBasicDto;
import com.alkemy.challenge.dto.CharacterDto;
import com.alkemy.challenge.dto.MovieDto;
import com.alkemy.challenge.service.CharacterService;
import com.alkemy.challenge.service.impl.CharacterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/")
    public ResponseEntity<List<CharacterDto>> getAll(){
        List<CharacterDto> characters = characterService.getAll();
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getById(@PathVariable Long id){
        CharacterDto dto = characterService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CharacterBasicDto>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) List<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order
            ){
        List<CharacterBasicDto> characters = characterService.getByFilters(name, age, weight, movies, order);
        return ResponseEntity.ok(characters);
    }

    @PostMapping
    public ResponseEntity<CharacterDto> save(@Valid @RequestBody CharacterDto dto){
        CharacterDto result = characterService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CharacterDto> update(@PathVariable Long id, @Valid @RequestBody CharacterDto dto) {
        CharacterDto result = characterService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
