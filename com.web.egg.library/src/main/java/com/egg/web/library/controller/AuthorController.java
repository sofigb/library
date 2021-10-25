
package com.egg.web.library.controller;

import com.egg.web.library.entity.Author;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.repository.AuthorRepository;
import com.egg.web.library.service.AuthorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

public class AuthorController {
    @Autowired
    AuthorService aservice;
     @Autowired
    AuthorRepository arep;
    
//    @GetMapping("/crear_autor")
//    public ModelAndView crearAuthor() {
//        ModelAndView mav = new ModelAndView("Author");
////       mav.addObject("title", "CREAR");
////        mav.addObject("action", "/guardar_a");
//        return mav;
//    }
//    
//    @PostMapping("/guardar_autor")
//    
//    public RedirectView guardarAuthor(@RequestParam String name) throws MyExceptionService{
//        aservice.getAuthor(name);
//        return new RedirectView ("/crear_autor");
//    }
    
    @GetMapping("/listarAutores")
    private List<Author> listaAuthor() {
        List <Author> usuarios = aservice.findAll();
        return usuarios;
    }
    @GetMapping("/mostrarAutor")
   public ModelAndView MostrarAuthor() {
        ModelAndView mav = new ModelAndView("/static/tables");
        mav.addObject("title", "mostrarAutor");
        mav.addObject("autores", listaAuthor());
       return mav;
   }




    @GetMapping("/altaAutor")
    private String altaAutor(@RequestParam String id) throws MyExceptionService {
     aservice.changeState(id, Boolean.TRUE);
        return "El autor " + aservice.lookForId(id).getName() +" ha sido dado de alta";
    }

    @GetMapping("/bajaAutor")
    private String bajaAutor(@RequestParam String id) throws MyExceptionService {
     aservice.changeState(id, Boolean.FALSE);
        return "El autor " + aservice.lookForId(id).getName() +" ha sido dado de baja";
    }



   @PostMapping("/crear_autor")
       public  void crearAutor(@RequestBody Author author)  { 
       aservice.crearAuthor(author);
    }
    @PutMapping("/modificar")
    public void modificar(@RequestBody Author author){
        aservice.crearAuthor(author);
    }


    @GetMapping("/foos")//?id="sdgfsdg" ->esto x usar REQUESTPARAM
    @ResponseBody
    public String getFooById(@RequestParam String id) {
        return "ID: " + id;
    }
    
}
