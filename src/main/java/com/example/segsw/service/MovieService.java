package com.example.segsw.service;


import com.example.segsw.model.MovieModel;

import java.util.List;

public interface MovieService {
    MovieModel saveMovie(MovieModel movieModel);
    List<MovieModel> getAllMovies();
    MovieModel getMovieById(long id);
    MovieModel updateMovie(MovieModel movieModel, long id);
    void deleteMovie(long id);
}
