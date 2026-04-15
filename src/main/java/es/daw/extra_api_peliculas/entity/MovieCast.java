package es.daw.extra_api_peliculas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "movie_cast")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieCast {

    // PK compuesta
    @EmbeddedId
    private MovieCastId id;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    private Actor actor;




    // propiedades propias de la tabla intermedia
    private int screenMinutes;

    @Column(precision = 12, scale = 2)
    private BigDecimal salaryOverride;

    private boolean active;





}
