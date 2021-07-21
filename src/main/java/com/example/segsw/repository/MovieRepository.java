package com.example.segsw.repository;


import com.example.segsw.model.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieModel, Long> {
}
