package com.alkemyjava.Huergo.services;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Transactional(readOnly = true)
    public List<Character> findAll() {
        return characterRepository.showAll();
    }

    @Transactional
    public Character create(Character character) {
        Character c = new Character();
        c.setImage(character.getImage());
        c.setName(character.getName());
        c.setAge(character.getAge());
        c.setWeight(character.getWeight());
        c.setStory(character.getStory());
        characterRepository.save(c);
        return c;
    }

    @Transactional
    public void delete(Long characterId) {
        characterRepository.deleteById(characterId);
    }

    @Transactional(readOnly = true)
    public Character findById(Long characterId) {
        Optional<Character> characterOptional = characterRepository.findById(characterId);
        return characterOptional.orElse(null);
    }

    @Transactional
    public void edit(Long characterId, byte[] image, String name, Long age, Long weight, String story) {
        characterRepository.modify(characterId, image, name, age, weight, story);
    }

    @Transactional(readOnly = true)
    public List<Character> allCharacters() {
        return characterRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Character> filter(String search, String filter, String filterText) {
        if (filter == null) {
            return characterRepository.findInTable(search);
        }
        List<Character> list = new ArrayList<>();
        switch (filter) {
            case "age":
                list = characterRepository.findByAge(search, filterText);
                break;
            case "movies":
                list = characterRepository.findByMovie(search, filterText);
                break;
        }
        return list;
    }
}
