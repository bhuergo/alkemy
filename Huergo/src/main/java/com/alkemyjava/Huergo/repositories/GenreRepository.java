package com.alkemyjava.Huergo.repositories;

import com.alkemyjava.Huergo.entities.Genre;
import com.alkemyjava.Huergo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Movie> findByGenreId(Long genreId);
}
