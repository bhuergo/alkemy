package com.alkemyjava.Huergo.repositories;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Modifying
    @Query("UPDATE Character c SET c.image = :image, c.name = :name, c.age = :age, c.weight = :weight, c.story = :story WHERE c.characterId = :characterId")
    void modify(@Param("characterId") Long characterId, @Param("image") String image, @Param("name") String name, @Param("age") Integer age, @Param("weight") Double weight, @Param("story") String story);

    List<Character> findByAge(Integer age);

    List<Character> findByMoviesIn(List<Movie> moviesIds);

    List<Character> findByNameContaining(String name);

    Boolean existsByCharacterId(Long id);

}
