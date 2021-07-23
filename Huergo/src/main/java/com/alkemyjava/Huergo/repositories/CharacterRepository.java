package com.alkemyjava.Huergo.repositories;

import com.alkemyjava.Huergo.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("SELECT c.image, c.name FROM Character c")
    public List<Character> showAll();
}
