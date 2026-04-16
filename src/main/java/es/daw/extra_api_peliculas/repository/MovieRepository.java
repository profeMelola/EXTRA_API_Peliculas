package es.daw.extra_api_peliculas.repository;

import es.daw.extra_api_peliculas.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

