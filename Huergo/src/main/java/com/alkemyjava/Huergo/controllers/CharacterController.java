package com.alkemyjava.Huergo.controllers;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    //mostrar listado de personajes
    @GetMapping("/all")
    public List<Character> showAll() {
        return characterService.findAll();
    }

    //crear nuevo personaje
    @GetMapping("/create")
    public HashMap create(@RequestParam Character newCharacter) {
        HashMap<String,String> response = new HashMap<>();
        try {
            Character character = characterService.create(newCharacter);
            response.put("response","Personaje " + character.getName() + " creado exitosamente");
            return response;
        } catch (Exception e) {
            response.put("response","Ocurrió un error" + e.getMessage());
            return response;
        }
    }

    //eliminar un personaje
    @PostMapping("/delete/{characterId}")
    public RedirectView delete(@PathVariable Long characterId) {
        characterService.delete(characterId);
        return new RedirectView("/characters/all"); //por ejemplo, luego de que se elimina un personaje que redireccione al listado de todos los personajes
    }

    //modificar un personaje
    @GetMapping("/edit/{characterId}")
    public ModelAndView edit(@PathVariable Long characterId) {
        ModelAndView mav = new ModelAndView(""); //vista formulario para introducir nuevos datos
        mav.addObject("character", characterService.findById(characterId));
        return mav;
    }

    @PostMapping("/modify")
    public RedirectView modify(@RequestParam Long characterId, @RequestParam byte[] image, @RequestParam String name, @RequestParam Long age, @RequestParam Long weight, @RequestParam String story) {
        characterService.edit(characterId, image, name, age, weight, story);
        return new RedirectView("/characters/all"); //por ejemplo, luego de que se modifica un personaje que redireccione al listado de todos los personajes
    }

    //mostrar detalles de un personaje
    @GetMapping("/details/{characterId}")
    public ModelAndView details(@PathVariable Long characterId) {
        ModelAndView mav = new ModelAndView("");
        mav.addObject("character", characterService.findById(characterId));
        return mav;
    }

    //búsqueda de personajes
    @GetMapping("/")
    @ResponseBody
    public List<Character> search(@RequestParam(name = "search", required = false) String search, @RequestParam(name = "filter", required = false) String filter, @RequestParam(name = "filterText", required = false) String filterText) {
        return characterService.filter(search, filter, filterText);
    }
}
