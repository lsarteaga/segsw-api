package com.example.segsw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "movies")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 50, message = "title should be between 5 - 50 characters")
    @NotEmpty
    private String title;

    @NotEmpty
    @Size(min = 10, max = 200, message = "description should be between 10 - 200 characters")
    private String description;

    @NotNull
    @Range(min = 1900, max = 2021, message = "year should be between 1900 - 2021")
    private int year;

    @NotNull
    @Range(min = 30, max = 240, message = "duration should be between 30 - 240")
    private int duration;

    @NotEmpty
    private String imageUrl;
}
