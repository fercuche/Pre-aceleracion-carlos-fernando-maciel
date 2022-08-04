package com.alkemy.challenge.repository;

import com.alkemy.challenge.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}