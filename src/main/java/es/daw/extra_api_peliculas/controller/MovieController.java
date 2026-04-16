package es.daw.extra_api_peliculas.controller;

import es.daw.extra_api_peliculas.dto.request.MovieCastRequestDto;
import es.daw.extra_api_peliculas.service.MovieCastService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieCastService movieCastService;

    // NO PERMITIDO EN EL EXAMEN. USAMOS LOMBOK!!!
//    public MovieController(MovieCastService movieCastService) {
//        this.movieCastService = movieCastService;
//    }

    /**
     * IA
     * pendiente!!! diferencia @ResponseStatus vs ResponseEntity
     * POST /movies/{id}/cast
     * Añade un actor a la película indicada.
     * Devuelve 201 Created si la operación se realiza correctamente.
     */
    @PostMapping("/{id}/cast2")
    @ResponseStatus(HttpStatus.CREATED)
    public void addActorToMovie2(
            @PathVariable Long id,
            @Valid @RequestBody MovieCastRequestDto dto) {

        movieCastService.addActorToMovie(id, dto);
    }

    @PostMapping("/{id}/cast")
    public ResponseEntity<Void> addActorToMovie(
            @PathVariable Long id,
            @Valid @RequestBody MovieCastRequestDto dto) {

            movieCastService.addActorToMovie(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

//  PENDIENTE DEVOLVER ESTE DTO
//    /** Respuesta para Movie */
//    public record MovieResponseDto(
//            Long id,
//            String title,
//            int releaseYear,
//            String genre,
//            boolean active
//    ) {}
}

