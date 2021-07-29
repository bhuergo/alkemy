package com.alkemyjava.Huergo.repositories;

import com.alkemyjava.Huergo.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("SELECT c.image, c.name FROM Character c")
    List<Character> showAll();

    @Modifying
    @Query("UPDATE Character c SET c.image = :image, c.name = :name, c.age = :age, c.weight = :weight, c.story = :story WHERE c.characterId = :characterId")
    void modify(@Param("characterId") Long characterId, @Param("image") byte[] image, @Param("name") String name, @Param("age") Long age, @Param("weight") Long weight, @Param("story") String story);

    @Query(value = "SELECT * FROM character c WHERE c.name like %:search% and c.age = :filterText", nativeQuery = true)
    List<Character> findByAge(@Param("search") String search, @Param("filterText") String filterText);

    @Query(value = "SELECT * FROM character c WHERE c.name like %:search% and :filterText in c.movies", nativeQuery = true)
    List<Character> findByMovie(@Param("search") String search, @Param("filterText") String filterText);

    @Query(value = "SELECT * FROM Character c WHERE c.name like %:search% or c.age = %:search% or c.weight = %:search%", nativeQuery = true)
    List<Character> findInTable(@Param("search") String search);

}
