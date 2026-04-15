package es.daw.extra_api_peliculas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MovieCastId implements Serializable {

    @Column(name="movie_id", nullable = false)
    private Long movieId;

    private Long castId;



}