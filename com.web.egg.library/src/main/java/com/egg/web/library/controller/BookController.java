
package com.egg.web.library.controller;

import com.egg.web.library.entity.Author;
import com.egg.web.library.entity.Editorial;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.service.BookService;
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
@RequestMapping("/libros")
public class BookController {
    @Autowired
    BookService bservice;
    
    @GetMapping()
    public ModelAndView MostrarAuthor() {
        ModelAndView mav = new ModelAndView("tablesBook");
        mav.addObject("libros", bservice.findAll());
        mav.addObject("href", "registrar");
        mav.addObject("title", "o Libro");
        mav.addObject("title1", "Libros"); 
        return mav;
    }

    @GetMapping("/registrar")
    public ModelAndView registrarAuthor() {
        ModelAndView mav = new ModelAndView("registerBook");
        mav.addObject("action", "crear");
        
        return mav;
    }
    @GetMapping("/modificarNombre/{id}")
    public ModelAndView modificarAuthor(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("modifyAuthor");   
        mav.addObject("objeto", bservice.lookForId(id));
        mav.addObject("action", "guardarNombre");
        mav.addObject("title1", " Cambios en autores");
        return mav;
    }

//    @GetMapping("/alta/{id}")
//    public RedirectView alta(@PathVariable String id) {
//        bservice.changeState(id, Boolean.TRUE);
//        return new RedirectView("/autores");
//    }
//
//    @GetMapping("/baja/{id}")
//    public RedirectView baja(@PathVariable String id) {
//        bservice.changeState(id, Boolean.FALSE);
//        return new RedirectView("/autores");
//    }
//
//    @PostMapping("/crear")
//    public RedirectView crearAutor(@RequestParam String isbn, @RequestParam String title,@RequestParam Integer year,@RequestParam Integer copies,@RequestParam String author , @RequestParam String editorial) throws MyExceptionService {
//       bservice.getBook( isbn,  title,  year,  copies,  author,  editorial);
//        return new RedirectView("/autores");
//    }
//    @PostMapping("/guardarNombre")
//    public RedirectView modificarAuthor(@RequestParam String name, @RequestParam  String id) throws MyExceptionService {
//        
//        bservice.modifyName(id, name);
//        return new RedirectView("/autores");
//    }
}
