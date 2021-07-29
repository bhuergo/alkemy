package com.alkemyjava.Huergo.controllers;

import com.alkemyjava.Huergo.entities.User;
import com.alkemyjava.Huergo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RestController
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

    //registro de usuario
    @PostMapping("/register")
    public HashMap register(@RequestBody User newUser) {
        HashMap<String,String> response = new HashMap<>();
        try {
            User user = userService.create(newUser);
            response.put("response","Usuario "+ user.getUsername() +" creado exitosamente");
            return response;
        } catch (Exception e) {
            response.put("response","Ocurrió un error" + e.getMessage());
            return response;
        }
    }
}
