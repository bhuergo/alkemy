package com.alkemyjava.Huergo.services;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.repositories.CharacterRepository;
import javassist.NotFoundException;
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
    public Character findById(Long characterId) throws NotFoundException {
        Optional<Character> characterOptional = characterRepository.findById(characterId);
        return characterOptional.orElseThrow(() -> new NotFoundException("El personaje no existe"));
    }

    @Transactional
    public Character edit(Character character) throws NotFoundException {
        Long id = character.getCharacterId();
        if (!characterRepository.existsByCharacterId(id)) {
            throw new NotFoundException("El personaje no existe");
        }
        return characterRepository.modify(character.getCharacterId(), character.getImage(), character.getName(), character.getAge(), character.getWeight(), character.getStory());
    }

    @Transactional(readOnly = true)
    public List<Character> allCharacters() {
        return characterRepository.findAll();
    }

}
