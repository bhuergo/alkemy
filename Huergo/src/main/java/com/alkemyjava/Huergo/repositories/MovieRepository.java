package com.alkemyjava.Huergo.repositories;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT m.image, m.title, m.creationDate FROM Movie m")
    List<Movie> showAll();

    @Modifying
    @Query("UPDATE Movie m SET m.image = :image, m.title = :title, m.creationDate = :creationDate, m.rating = :rating, m.characters = :characters WHERE m.movieId = :movieId")
    Movie modify(@Param("movieId") Long movieId, @Param("image") String image, @Param("title") String title, @Param("creationDate") Date creationDate, @Param("rating") Integer rating, @Param("characters") List<Character> characters);

    List<Movie> findByTitle(String title);

    @Query(value = "SELECT m FROM Movie m ORDER BY ASC", nativeQuery = true)
    List<Movie> orderAsc();

    @Query(value = "SELECT m FROM Movie m ORDER BY DESC", nativeQuery = true)
    List<Movie> orderDesc();

}
