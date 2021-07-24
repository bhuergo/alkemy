package com.alkemyjava.Huergo.controllers;

import com.alkemyjava.Huergo.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    //mostrar listado de personajes
    @GetMapping("/all")
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView(""); //vista en donde se muestren los personajes
        mav.addObject("characters", characterService.findAll());
        return mav;
    }

    //crear nuevo personaje
    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView(""); //vista formulario para crear el personaje
    }

    @PostMapping("/save")
    public RedirectView save(@RequestParam byte[] image, @RequestParam String name, @RequestParam Long age, @RequestParam Long weight, @RequestParam String history) {
        characterService.create(image,name,age,weight,history);
        return new RedirectView("/characters/all"); //por ejemplo, luego de que se crea un nuevo personaje que redireccione al listado de todos los personajes
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
    public RedirectView modify(@RequestParam Long characterId, @RequestParam byte[] image, @RequestParam String name, @RequestParam Long age, @RequestParam Long weight, @RequestParam String history) {
        characterService.edit(characterId,image,name,age,weight,history);
        return new RedirectView("/characters/all"); //por ejemplo, luego de que se modifica un personaje que redireccione al listado de todos los personajes
    }

    //mostrar detalles de un personaje
    @GetMapping("/details/{characterId}")
    public ModelAndView details(@PathVariable Long characterId) {
        ModelAndView mav = new ModelAndView("");
        mav.addObject("character", characterService.findById(characterId));
        return mav;
    }
}
