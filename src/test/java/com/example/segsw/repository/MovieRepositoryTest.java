package com.example.segsw.repository;

import com.example.segsw.model.MovieModel;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    // Junit test
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveMovieTest() {
        MovieModel movieModel = MovieModel
                .builder()
                .title("Parasite")
                .description("Greed and class discrimination threaten the newly " +
                        "formed symbiotic relationship between the wealthy Park family " +
                        "and the destitute Kim clan.")
                .year(2019)
                .duration(132)
                .imageUrl("https://www.imdb.com/title/tt6751668/mediaviewer/rm3194916865/?ref_=tt_ov_i")
                .build();

        movieRepository.save(movieModel);
        MatcherAssert.assertThat(movieModel, CoreMatchers.isA(MovieModel.class));
        // MatcherAssert.assertThat(movieModel, CoreMatchers.notNullValue());
        // MatcherAssert.assertThat(movieModel.getTitle(), CoreMatchers.equalTo("Parasite"));
        // MatcherAssert.assertThat(movieModel, CoreMatchers.instanceOf(MovieModel.class));
        // MatcherAssert.assertThat(movieModel.getId(), CoreMatchers.nullValue());
        // MatcherAssert.assertThat(movieModel.getId(), CoreMatchers.equalTo(1L));
        // MatcherAssert.assertThat(movieModel.getImageUrl(), CoreMatchers.startsWithIgnoringCase("H"));
    }

    @Test
    @Order(2)
    public void getMovieTest() {
        MovieModel movieModel = movieRepository.findById(1L).get();
        MatcherAssert.assertThat(movieModel.getId(), Matchers.equalTo(1L));
    }

    @Test
    @Order(3)
    public void getListOfMoviesTest() {
        List<MovieModel> movies = movieRepository.findAll();
        MatcherAssert.assertThat(movies.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateMovieTest() {
        MovieModel movieModel = movieRepository.findById(1L).get();
        movieModel.setTitle("Parasite (2019)");
        MovieModel updatedMovie = movieRepository.save(movieModel);
        MatcherAssert.assertThat(updatedMovie.getTitle(), Matchers.equalTo("Parasite (2019)"));
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteMovieTest() {
        movieRepository.deleteById(1L);
        Optional<MovieModel> optionalMovieModel = movieRepository.findByTitle("Parasite (2019)");

        if (optionalMovieModel.isPresent()) {
            optionalMovieModel.get();
        }
        MatcherAssert.assertThat(optionalMovieModel, Matchers.is(Optional.empty()));
    }
}
