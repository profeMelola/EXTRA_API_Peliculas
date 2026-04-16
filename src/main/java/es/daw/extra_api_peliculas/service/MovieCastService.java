package es.daw.extra_api_peliculas.service;

import es.daw.extra_api_peliculas.dto.request.MovieCastRequestDto;
import es.daw.extra_api_peliculas.entity.Actor;
import es.daw.extra_api_peliculas.entity.Movie;
import es.daw.extra_api_peliculas.entity.MovieCast;
import es.daw.extra_api_peliculas.entity.MovieCastId;
import es.daw.extra_api_peliculas.repository.ActorRepository;
import es.daw.extra_api_peliculas.repository.MovieCastRepository;
import es.daw.extra_api_peliculas.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MovieCastService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final MovieCastRepository movieCastRepository;

//    public MovieCastService(MovieRepository movieRepository, ActorRepository actorRepository) {
//        this.movieRepository = movieRepository;
//        this.actorRepository = actorRepository;
//    }

    /**
     * Añade un actor a una película creando un nuevo MovieCast.
     *
     * @param movieId  id de la película (path variable)
     * @param dto      datos del cast recibidos en el body
     * @throws ResponseStatusException 404 si la película o el actor no existen
     * @throws ResponseStatusException 409 si el actor ya tiene un rol en esa película
     */
    @Transactional
    public void addActorToMovie(Long movieId, MovieCastRequestDto dto) {

        // 1. Buscar película
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Película no encontrada con id: " + movieId));

        // 2. Buscar actor
        Actor actor = actorRepository.findById(dto.actorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Actor no encontrado con id: " + dto.actorId()));

        // 3. Comprobar duplicado (mismo actor en la misma película)
        MovieCastId castId = new MovieCastId(movieId, dto.actorId());
        boolean alreadyExists = movie.getMovieCast().stream()
                .anyMatch(mc -> mc.getId().equals(castId));

        // Necesita crear la excepción propia ConflictException
//        if (movieCastRepository.existsById(castId)) {
//            throw new ConflictException(
//                    "El actor '%s' ya tiene un rol en la película '%s'"
//                            .formatted(actor.getStageName(), movie.getTitle()));
//        }

        if (alreadyExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El actor ya tiene un rol en esta película");
        }

        // 4. Crear MovieCast y asociar
        MovieCast movieCast = new MovieCast();
        movieCast.setId(castId);
        movieCast.setActor(actor);
        movieCast.setCharacterName(dto.characterName());
        movieCast.setScreenMinutes(dto.screenMinutes());
        movieCast.setSalaryOverride(dto.salaryOverride());

        movie.addMovieCast(movieCast);

        // Se salva automáticamente el entity movie
        // Al ser cascade ALL, no es necesario guardar explícitamente movieCast
    }
}

