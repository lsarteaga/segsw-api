package com.example.segsw.service.Implementation;

import com.example.segsw.exception.ResourceNotFoundException;
import com.example.segsw.model.MovieModel;
import com.example.segsw.repository.MovieRepository;
import com.example.segsw.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplementation implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImplementation(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieModel saveMovie(MovieModel movieModel) {
        return movieRepository.save(movieModel);
    }

    @Override
    public List<MovieModel> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public MovieModel getMovieById(long id) {
        return movieRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
    }

    @Override
    public MovieModel updateMovie(MovieModel movieModel, long id) {
        MovieModel model = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        model.setTitle(movieModel.getTitle());
        model.setDescription(movieModel.getDescription());
        model.setYear(movieModel.getYear());
        model.setDuration(movieModel.getDuration());
        model.setImageUrl(movieModel.getImageUrl());

        return movieRepository.save(model);
    }

    @Override
    public String deleteMovie(long id) {
        movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        movieRepository.deleteById(id);
        return "Movie deleted";
    }
}

