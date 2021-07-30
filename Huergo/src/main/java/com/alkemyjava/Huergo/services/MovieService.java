package com.alkemyjava.Huergo.services;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.entities.Movie;
import com.alkemyjava.Huergo.repositories.CharacterRepository;
import com.alkemyjava.Huergo.repositories.MovieRepository;
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
    public List<Movie> findAll() {
        return movieRepository.showAll();
    }

    @Transactional(readOnly = true)
    public Movie findById(Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        return movieOptional.orElse(null);
    }

    @Transactional
    public void create(byte[] image, String title, Date creationDate, Integer rating, List<Character> characters) {
        Movie movie = new Movie();
        movie.setImage(image);
        movie.setTitle(title);
        movie.setCreationDate(creationDate);
        movie.setRating(rating);
        List<Character> movieCharacters = new ArrayList<>();
        for (Character c : characters) {
            Optional<Character> characterOptional = characterRepository.findById(c.getCharacterId());
            Character chara = characterOptional.orElse(null);
            if(characterOptional != null) {
                movieCharacters.add(chara);
            }
        }
        movie.setCharacters(movieCharacters);
        movieRepository.save(movie);
    }

    @Transactional
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Transactional
    public void edit(Long movieId, byte[] image, String title, Date creationDate, Long rating, List<Character> characters) {
        movieRepository.modify(movieId,image,title,creationDate,rating,characters);
    }
}
