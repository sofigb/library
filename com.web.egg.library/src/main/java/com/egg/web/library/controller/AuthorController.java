
package com.egg.web.library.controller;

import com.egg.web.library.entity.Author;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.repository.AuthorRepository;
import com.egg.web.library.service.AuthorService;

import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/mostrarAutores")
   public ModelAndView MostrarAuthor() {
        ModelAndView mav = new ModelAndView("listarAuthor");
        mav.addObject("title", "listarAuthor");
        mav.addObject("autores", listaAuthor());
       return mav;
   }

   @Deprecated
   @GetMapping("/activarAutor")
    public ModelAndView activarAutor(@RequestParam String id) throws MyExceptionService {
        String mensaje = altaAutor(id);
        return MostrarAuthor();
   }

    @GetMapping("/altaAutor")
    private String altaAutor(@RequestParam String id) throws MyExceptionService {
     aservice.changeState(id, Boolean.TRUE);
        String mensaje = "El autor " + aservice.lookForId(id).getName() +" ha sido dado de alta";
        final String json = "{\"mensaje\":\" "+mensaje+" \",\"status\":\""+Boolean.TRUE+"\"}";
        return json;
    }

    @Deprecated
    @GetMapping("/desactivarAutor")
    public ModelAndView desactivarAutor(@RequestParam String id) throws MyExceptionService {
        String mensaje = bajaAutor(id);
        return MostrarAuthor();
    }

    @GetMapping("/bajaAutor")
    private String bajaAutor(@RequestParam String id) throws MyExceptionService {
     aservice.changeState(id, Boolean.FALSE);
        String mensaje = "El autor " + aservice.lookForId(id).getName() +" ha sido dado de baja";
        final String json = "{\"mensaje\":\" "+mensaje+" \",\"status\":\""+Boolean.FALSE+"\"}";
        return json;
    }



   @PostMapping("/crear_autor")
       public  void crearAutor(@RequestBody String nombre) {
        nombre = nombre.replaceAll("\"","");
       Author unAutor = new Author();
       unAutor.setName(nombre);
       aservice.crearAuthor(unAutor);
    }


    @GetMapping("/crearAutor")
    public ModelAndView CrearAuthor() {
        ModelAndView mav = new ModelAndView("crearAutor");
        mav.addObject("title", "CrearAutor");
        return mav;
    }


    @GetMapping("/modificarAutor")
    public ModelAndView ModificarAuthor(@RequestParam String id) {
        Author autor = getFooById(id);
        ModelAndView mav = new ModelAndView("modificarAuthor");
        mav.addObject("title", "modificarAuthor");
        mav.addObject("autor", autor);
        return mav;
    }


    @PutMapping("/modificar")
    public void modificar(@RequestBody String autor){
         Author unAutor = new Author();
         unAutor.setId(autor.split(",")[0].split(":")[1]);
         unAutor.setName(autor.split(",")[1].split(":")[1]);
         unAutor.setStatus(Boolean.getBoolean(autor.split(",")[2].split(":")[1]));
        aservice.modificarAuthor(unAutor);

    }

    @GetMapping("/obtenerAutor")
    public Author getFooById(@RequestParam String id) {
        return  aservice.lookForId(id);
    }
    
}
