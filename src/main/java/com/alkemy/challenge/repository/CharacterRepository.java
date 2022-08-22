package com.alkemy.challenge.repository;

import com.alkemy.challenge.entity.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findAll(Specification<Character> specification);

    boolean existsByName(String name);


}