package com.alkemyjava.Huergo.controllers;

import com.alkemyjava.Huergo.entities.User;
import com.alkemyjava.Huergo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    //ingreso de usuario registrado
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView(""); //vista formulario login
        mav.addObject("user", new User());
        return mav;
    }

    @GetMapping("/index")
    public ModelAndView access() {
        return new ModelAndView(""); //vista inicial una vez autenticado
    }

    //registro de usuario
    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView(""); //vista formulario registro
    }

    @PostMapping("/create")
    public RedirectView save(@RequestParam String username, @RequestParam String password) {
        userService.create(username,password);
        return new RedirectView("/auth/login");
    }
}
