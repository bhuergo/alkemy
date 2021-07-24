package com.alkemyjava.Huergo.controllers;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.services.CharacterService;
import com.alkemyjava.Huergo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    @Autowired
    CharacterService characterService;

    //mostrar listado de películas
    @GetMapping("/all")
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView(""); //vista en donde se muestren las series y películas
        mav.addObject("movies", movieService.findAll());
        return mav;
    }

    //mostrar detalles de un personaje
    @GetMapping("/details/{movieId}")
    public ModelAndView details(@PathVariable Long movieId) {
        ModelAndView mav = new ModelAndView("");
        mav.addObject("movie", movieService.findById(movieId));
        return mav;
    }

    //crear nueva película
    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView(""); //vista formulario para crear la película o serie
        mav.addObject("characters", characterService.allCharacters());
        return mav;
    }

    @PostMapping("/save")
    public RedirectView save(@RequestParam byte[] image, @RequestParam String title, @RequestParam Date creationDate, @RequestParam Long rating, @RequestParam("characters")List<Character> characters) {
        movieService.create(image, title, creationDate, rating, characters);
        return new RedirectView("/movies/all"); //por ejemplo, luego de que se crea una nueva película que redireccione al listado de todas las películas
    }

    //eliminar una película
    @PostMapping("/delete/{movieId}")
    public RedirectView delete(@PathVariable Long movieId) {
        movieService.delete(movieId);
        return new RedirectView("/movies/all"); //por ejemplo, luego de que se elimina una película que redireccione al listado de todas las películas
    }

    //modificar una película
    @GetMapping("/edit/{movieId}")
    public ModelAndView edit(@PathVariable Long movieId) {
        ModelAndView mav = new ModelAndView(""); //vista formulario para introducir nuevos datos
        mav.addObject("movie", movieService.findById(movieId));
        mav.addObject("characters", characterService.allCharacters());
        return mav;
    }

    @PostMapping("/modify")
    public RedirectView modify(@RequestParam Long movieId, @RequestParam byte[] image, @RequestParam String title, @RequestParam Date creationDate, @RequestParam Long rating, @RequestParam("characters")List<Character> characters) {
        movieService.edit(movieId, image, title, creationDate, rating, characters);
        return new RedirectView("/movies/all"); //por ejemplo, luego de que se modifica una película que redireccione al listado de todas las películas
    }
}
