package com.egg.web.library.controller;

import com.egg.web.library.entity.Author;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.repository.AuthorRepository;
import com.egg.web.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/autores")
public class AuthorController {

    @Autowired
    AuthorService aservice;

    @GetMapping()
    public ModelAndView MostrarAuthor() {
        ModelAndView mav = new ModelAndView("tables");
        mav.addObject("listaOb", aservice.findAll());
        mav.addObject("href1", "autores");
        mav.addObject("href", "registrar");
        mav.addObject("title", "o Autor");
        mav.addObject("title1", "Autores"); 
        return mav;
    }

    @GetMapping("/registrar")
    public ModelAndView registrarAuthor() {
        ModelAndView mav = new ModelAndView("registerAuthor");
        mav.addObject("action", "crear");
        
        return mav;
    }
    @GetMapping("/modificarNombre/{id}")
    public ModelAndView modificarAuthor(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("modifyAuthor");   
        mav.addObject("objeto", aservice.lookForId(id));
        mav.addObject("action", "guardarNombre");
        mav.addObject("title1", " Cambios en autores");
        return mav;
    }

    @GetMapping("/alta/{id}")
    public RedirectView alta(@PathVariable String id) {
        aservice.changeState(id, Boolean.TRUE);
        return new RedirectView("/autores");
    }

    @GetMapping("/baja/{id}")
    public RedirectView baja(@PathVariable String id) {
        aservice.changeState(id, Boolean.FALSE);
        return new RedirectView("/autores");
    }

    @PostMapping("/crear")
    public RedirectView crearAutor(@RequestParam String name) throws MyExceptionService {
        aservice.createAuthor(name);
        return new RedirectView("/autores");
    }
    @PostMapping("/guardarNombre")
    public RedirectView modificarAuthor(@RequestParam String name, @RequestParam  String id) throws MyExceptionService {
        
        aservice.modifyName(id, name);
        return new RedirectView("/autores");
    }
}

