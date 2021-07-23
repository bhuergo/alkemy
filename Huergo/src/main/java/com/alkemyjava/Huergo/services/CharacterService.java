package com.alkemyjava.Huergo.services;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    public List<Character> findAll() {
        return characterRepository.findAll();
    }
}
