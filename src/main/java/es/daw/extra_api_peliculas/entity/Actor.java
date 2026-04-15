package es.daw.extra_api_peliculas.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "actors")
public class Actor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String stageName;

    @Column(nullable = false, length = 120)
    private String fullName;

    @Column(length = 60)
    private String nationality;

    @Column(nullable = false)
    private boolean active = true;

    // Pendiente!!!! pongo cascade all y orphanRemoval????
    @OneToMany(mappedBy = "actor")
    private Set<MovieCast>  movieCast;

    // ---------- el helpers bidireccionales ------------
    public void addMovieCast(MovieCast movieCast){
        this.movieCast.add(movieCast);
        movieCast.setActor(this);
    }

    public void removeMovieCast(MovieCast movieCast){
        this.movieCast.remove(movieCast);
        movieCast.setActor(null);
    }

}