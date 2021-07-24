package com.alkemyjava.Huergo.services;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void create(byte[] image, String name, Long age, Long weight, String history) {
        Character character = new Character();
        character.setImage(image);
        character.setName(name);
        character.setAge(age);
        character.setWeight(weight);
        character.setHistory(history);
        characterRepository.save(character);
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
    public void edit(Long characterId, byte[] image, String name, Long age, Long weight, String history) {
        characterRepository.modify(characterId, image, name, age, weight, history);
    }

    @Transactional(readOnly = true)
    public List<Character> allCharacters() {
        return characterRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Character> filter(String search, String filter) {

        return characterRepository.filtered();
    }
}
