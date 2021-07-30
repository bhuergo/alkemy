package com.alkemyjava.Huergo.services;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.entities.Movie;
import com.alkemyjava.Huergo.repositories.CharacterRepository;
import com.alkemyjava.Huergo.repositories.MovieRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CharacterRepository characterRepository;

    @Transactional(readOnly = true)
    public List<Movie> findAll(Object o) {
        if (o instanceof String) {
            String content = (String) o;
            if ("ASC".equals(content)) {
                return movieRepository.orderAsc();
            }
            if ("DESC".equals(content)) {
                return movieRepository.orderDesc();
            }
            return movieRepository.findByTitle(content);
        }
        if (o instanceof Long) {
            Long genreId = (Long) o;

        }
        return movieRepository.showAll();
    }

    @Transactional(readOnly = true)
    public Movie findById(Long movieId) throws NotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        return movieOptional.orElseThrow(() -> new NotFoundException("La película o serie no existe"));
    }

    @Transactional
    public Movie create(Movie movie) {
        Movie m = new Movie();
        m.setImage(movie.getImage());
        m.setTitle(movie.getTitle());
        m.setCreationDate(movie.getCreationDate());
        m.setRating(movie.getRating());
        List<Character> movieCharacters = new ArrayList<>();
        for (Character c : movie.getCharacters()) {
            Optional<Character> characterOptional = characterRepository.findById(c.getCharacterId());
            Character chara = characterOptional.orElse(null);
            if(characterOptional != null) {
                movieCharacters.add(chara);
            }
        }
        m.setCharacters(movieCharacters);
        movieRepository.save(m);
        return m;
    }

    @Transactional
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Transactional
    public Movie edit(Movie movie) throws NotFoundException {
        Long id = movie.getMovieId();
        if (!movieRepository.existsById(id)) {
            throw new NotFoundException("La película o serie no existe");
        }
        return movieRepository.modify(movie.getMovieId(), movie.getImage(), movie.getTitle(), movie.getCreationDate(), movie.getRating(),movie.getCharacters());
    }
}
