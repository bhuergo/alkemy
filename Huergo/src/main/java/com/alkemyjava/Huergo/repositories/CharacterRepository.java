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
    public List<Character> showAll();

    @Modifying
    @Query("UPDATE Character c SET c.image = :image, c.name = :name, c.age = :age, c.weight = :weight, c.history = :history WHERE c.characterId = :characterId")
    public void modify(@Param("characterId") Long characterId, @Param("image") byte[] image, @Param("name") String name, @Param("age") Long age, @Param("weight") Long weight, @Param("history") String history);
}
