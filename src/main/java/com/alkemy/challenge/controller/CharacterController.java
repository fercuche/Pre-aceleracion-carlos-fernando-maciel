package com.alkemy.challenge.controller;

import com.alkemy.challenge.dto.CharacterDto;
import com.alkemy.challenge.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/all")
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
    public ResponseEntity<List<CharacterDto>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) List<Long> movies
            ){
        List<CharacterDto> characterDtos = characterService.getByFilters(name, age, weight, movies);
        return ResponseEntity.ok(characterDtos);
    }

    @PostMapping
    public ResponseEntity<CharacterDto> save(@RequestBody CharacterDto dto){
        CharacterDto result = characterService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CharacterDto> update(@PathVariable Long id, @RequestBody CharacterDto dto) {
        CharacterDto result = characterService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
