package com.alkemyjava.Huergo.controllers;

import com.alkemyjava.Huergo.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    //mostrar listado de personajes
    @GetMapping("/all")
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView("");
        mav.addObject("characters", characterService.findAll());
        return mav;
    }


}
