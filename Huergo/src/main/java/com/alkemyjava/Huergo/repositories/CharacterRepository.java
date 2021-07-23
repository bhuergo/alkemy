package com.alkemyjava.Huergo.repositories;

import com.alkemyjava.Huergo.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

}
