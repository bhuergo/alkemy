package com.alkemyjava.Huergo.controllers;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.entities.CharacterDTO;
import com.alkemyjava.Huergo.entities.Movie;
import com.alkemyjava.Huergo.services.CharacterService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    //mostrar listado de personajes y buscar con filtros
    @GetMapping
    public ResponseEntity<List<CharacterDTO>> showAll(@RequestParam(required = false) String name,
                                                      @RequestParam(required = false) Integer age,
                                                      @RequestParam(required = false) List<Movie> movies) {
        if (name != null) {
            return ResponseEntity.ok(characterService.findAll(name));
        }
        if (age != null) {
            return ResponseEntity.ok(characterService.findAll(age));
        }
        if (movies != null) {
            return ResponseEntity.ok(characterService.findAll(movies));
        }
        return ResponseEntity.ok(characterService.findAll(null));
    }

    //crear nuevo personaje
    @PostMapping
    public ResponseEntity<Character> create(@RequestBody @Valid Character newCharacter) throws Exception {
        return new ResponseEntity<>(characterService.create(newCharacter), HttpStatus.CREATED);
    }

    //eliminar un personaje
    @DeleteMapping("/{characterId}")
    public ResponseEntity<Void> delete(@PathVariable Long characterId) throws NotFoundException {
        characterService.delete(characterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //modificar un personaje
    @PutMapping
    public ResponseEntity<Character> edit(@RequestBody @Valid Character character) throws NotFoundException {
        return ResponseEntity.ok(characterService.edit(character));
    }

    //mostrar detalles de un personaje
    @GetMapping("/{characterId}")
    public ResponseEntity<Character> details(@PathVariable Long characterId) throws NotFoundException {
        return ResponseEntity.ok(characterService.findById(characterId));
    }

}
