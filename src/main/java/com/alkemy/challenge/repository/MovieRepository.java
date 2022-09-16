package com.alkemy.challenge.repository;

import com.alkemy.challenge.entity.Character;
import com.alkemy.challenge.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAll(Specification<Movie> spec);
}