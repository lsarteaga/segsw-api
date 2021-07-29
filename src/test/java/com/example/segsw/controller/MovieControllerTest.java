package com.example.segsw.controller;

import com.example.segsw.model.MovieModel;
import com.example.segsw.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void saveMovie() throws Exception {
        String url = "/api/movies/create";
        MovieModel movie1 = new MovieModel()
                .builder()
                .id(1L)
                .title("Joker")
                .description("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated" +
                        " by society. He then embarks on a downward spiral of revolution and bloody crime.")
                .year(2019)
                .duration(122)
                .imageUrl("https://www.imdb.com/title/tt7286456/mediaviewer/rm3353122305/?ref_=tt_ov_i")
                .build();

        when(movieService.saveMovie(any(MovieModel.class)))
                .thenReturn(movie1);
        mockMvc.perform(post(url)
                .content(asJsonString(movie1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Lista todas las peliculas")
    public void getAllMovies() throws Exception {

        String url = "/api/movies";
        List<MovieModel> movies = new ArrayList<>();
        MovieModel movie1 = new MovieModel()
                .builder()
                .id(1L)
                .title("Joker")
                .description("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated" +
                        " by society. He then embarks on a downward spiral of revolution and bloody crime.")
                .year(2019)
                .duration(122)
                .imageUrl("https://www.imdb.com/title/tt7286456/mediaviewer/rm3353122305/?ref_=tt_ov_i")
                .build();

        MovieModel movie2 = new MovieModel()
                .builder()
                .id(2L)
                .title("Fight Club")
                .description("An insomniac office worker and a devil-may-care soap maker form an underground" +
                        " fight club that evolves into much more.")
                .year(1999)
                .duration(139)
                .imageUrl("https://www.imdb.com/title/tt0137523/mediaviewer/rm1412004864/?ref_=tt_ov_i")
                .build();

        movies.add(movie1);
        movies.add(movie2);

        when(movieService.getAllMovies()).thenReturn(movies);
        mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", Matchers.is(2)));

    }

    @Test
    public void getMovieById() throws Exception {
        String url = "/api/movies/1";
        MovieModel movie1 = new MovieModel()
                .builder()
                .id(1L)
                .title("Joker")
                .description("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated" +
                        " by society. He then embarks on a downward spiral of revolution and bloody crime.")
                .year(2019)
                .duration(122)
                .imageUrl("https://www.imdb.com/title/tt7286456/mediaviewer/rm3353122305/?ref_=tt_ov_i")
                .build();

        when(movieService.getMovieById(1L)).thenReturn(movie1);
        this.mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", Matchers.is("Joker")));
    }

    @Test
    public void updateMovie() throws Exception {
        String url = "/api/movies/1/update";
        MovieModel movie1 = new MovieModel()
                .builder()
                .id(1L)
                .title("Joker")
                .description("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated" +
                        " by society. He then embarks on a downward spiral of revolution and bloody crime.")
                .year(2019)
                .duration(122)
                .imageUrl("https://www.imdb.com/title/tt7286456/mediaviewer/rm3353122305/?ref_=tt_ov_i")
                .build();

        when(movieService.updateMovie(movie1, 1L))
                .thenReturn(movie1);
        mockMvc.perform(put(url)
                .content(asJsonString(movie1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    @Test
    public void deleteMovie() throws Exception {
        String url = "/api/movies/{id}/delete";

        when(movieService.deleteMovie(1L)).thenReturn("Movie deleted");
        mockMvc.perform(delete(url, 1L)).andExpect(status().isOk()).andDo(print());

    }
}
