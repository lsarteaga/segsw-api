package com.example.segsw.controller;

import com.example.segsw.model.MovieModel;
import com.example.segsw.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        super();
        this.movieService = movieService;
    }

    @PostMapping("/create")
    public ResponseEntity<MovieModel> saveMovie(@Valid @RequestBody MovieModel movieModel) {
        return new ResponseEntity<>(movieService.saveMovie(movieModel), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieModel> getMovieById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<MovieModel> updateMovie(@Valid @RequestBody MovieModel movieModel,
                                                  @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(movieService.updateMovie(movieModel, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteMovie(@PathVariable(name = "id") long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie deleted", HttpStatus.OK);
    }
}
