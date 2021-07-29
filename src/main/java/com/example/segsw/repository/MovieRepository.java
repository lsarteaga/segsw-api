package com.example.segsw.repository;


import com.example.segsw.model.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieModel, Long> {
    Optional<MovieModel> findByTitle(String title);
}
