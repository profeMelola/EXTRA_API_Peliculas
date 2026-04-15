package es.daw.extra_api_peliculas.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(nullable = false)
    private int releaseYear;

    @Column(nullable = false, length = 30)
    private String genre;

    @Column(nullable = false)
    private boolean active = true;

    // combinación de cascde all + orpahnRemoval: si se elimina un MovieCast de la colección
    // se borrar su fila en movie_cast (Prueba 8)
    // Si se elimina una película se borran los MovieCast con id de dicha película
    // Si queda un movieCast con movie a null se borra.
    @OneToMany(mappedBy = "movie",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private Set<MovieCast> movieCast;

    // ---------- el helpers bidireccionales ------------
    public void addMovieCast(MovieCast movieCast){
        this.movieCast.add(movieCast);
        movieCast.setMovie(this);
    }

    public void removeMovieCast(MovieCast movieCast){
        this.movieCast.remove(movieCast);
        movieCast.setMovie(null);
    }

}
