package com.egg.web.library.controller;

import com.egg.web.library.entity.Editorial;
import com.egg.web.library.service.EditorialService;

import com.egg.web.library.exception.MyExceptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/editoriales")
public class EditorialController {

    @Autowired
    EditorialService eservice;

    @GetMapping()
    public ModelAndView MostrarEditorial() {
        ModelAndView mav = new ModelAndView("tables");
        mav.addObject("listaOb", eservice.findAll());
        mav.addObject("href1", "editoriales");
        mav.addObject("href", "registrar");
        mav.addObject("title", "a Editorial");
        mav.addObject("title1", "Editoriales");
        
        return mav;
    }

    @GetMapping("/registrar")
    public ModelAndView registrarEditorial() {
        ModelAndView mav = new ModelAndView("registerEditorial");
        mav.addObject("action", "crear");
        
        return mav; 
    }

    @GetMapping("/modificarNombre/{id}")
    public ModelAndView modificarEditorial(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("modifyEditorial");

        mav.addObject("objeto", eservice.lookForId(id));
        mav.addObject("action", "guardarNombre");
        mav.addObject("title1", " Cambios en editoriales");
        return mav;
    }

    @GetMapping("/alta/{id}")
    public RedirectView alta(@PathVariable String id) {
        eservice.changeState(id, Boolean.TRUE);
        return new RedirectView("/editoriales");
    }

    @GetMapping("/baja/{id}")
    public RedirectView baja(@PathVariable String id) {
        eservice.changeState(id, Boolean.FALSE);
        return new RedirectView("/editoriales");
    }

    @PostMapping("/crear")
    public RedirectView crearEditorial(@RequestParam String name)  {
        eservice.crearEditorial(name);
        return new RedirectView("/editoriales");
    }

    @PostMapping("/guardarNombre")
    public RedirectView modificarEditorial(@RequestParam String name, @RequestParam String id) throws MyExceptionService {

        eservice.modifyName(id, name);
        return new RedirectView("/editoriales");
    }
}
