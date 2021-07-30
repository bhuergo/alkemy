package com.alkemyjava.Huergo.controllers;

import com.alkemyjava.Huergo.entities.Movie;
import com.alkemyjava.Huergo.entities.MovieDTO;
import com.alkemyjava.Huergo.services.CharacterService;
import com.alkemyjava.Huergo.services.MovieService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    @Autowired
    CharacterService characterService;

    //mostrar listado de películas y buscar con filtros
    @GetMapping
    public ResponseEntity<List<MovieDTO>> showAll(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) Long genre,
                                                  @RequestParam(required = false) String order) {
        if (title != null) {
            return ResponseEntity.ok(movieService.findAll(title));
        }
        if (genre != null) {
            return ResponseEntity.ok(movieService.findAll(genre));
        }
        if (order != null) {
            return ResponseEntity.ok(movieService.findAll(order));
        }
        return ResponseEntity.ok(movieService.findAll(null));
    }

    //mostrar detalles de una película
    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> details(@PathVariable Long movieId) throws NotFoundException {
        return ResponseEntity.ok(movieService.findById(movieId));
    }

    //crear nueva película
    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody @Valid Movie newMovie) {
        return new ResponseEntity<>(movieService.create(newMovie), HttpStatus.CREATED);
    }

    //eliminar una película
    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> delete(@PathVariable Long movieId) throws NotFoundException {
        movieService.delete(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //modificar una película
    @PutMapping
    public ResponseEntity<Movie> edit(@RequestBody @Valid Movie movie) throws NotFoundException {
        return ResponseEntity.ok(movieService.edit(movie));
    }
}
