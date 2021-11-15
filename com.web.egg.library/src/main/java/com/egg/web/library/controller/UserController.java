package com.egg.web.library.controller;

import com.egg.web.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    @Autowired
    private UserService usuarioService;

    @GetMapping("/login") // required false hace qeu el parametro sea OPCIONAL , puede estar o no
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("login");

        if (error != null) {
            modelAndView.addObject("error", "Usuario o contraseña inválida");
        }

        if (logout != null) {
            modelAndView.addObject("logout", "Ha salido correctamente de la plataforma");
        }

        if (principal != null) {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView signup(HttpServletRequest request, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("registerUser");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            modelAndView.addObject("exito", flashMap.get("exito"));
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("username", flashMap.get("surname"));
            modelAndView.addObject("password", flashMap.get("pasword"));
            
        }

        if (principal != null) {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @PostMapping("/registro")
    public RedirectView signup(@RequestParam String username, @RequestParam String password, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView("/login");

        try {
            usuarioService.createUser(username, password);

            attributes.addFlashAttribute("exito", "El registro ha sido realizado satisfactoriamente");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            attributes.addFlashAttribute("username", username);
            attributes.addFlashAttribute("password", password);
       

            redirectView.setUrl("/registerUser");
        }

        return redirectView;
    }
}
