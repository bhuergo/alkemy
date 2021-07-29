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
    void modify(@Param("movieId") Long movieId, @Param("image") byte[] image, @Param("title") String title, @Param("creationDate") Date creationDate, @Param("rating") Long rating, @Param("characters") List<Character> characters);
}
