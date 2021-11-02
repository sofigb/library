
package com.egg.web.library.controller;

import com.egg.web.library.entity.Author;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.repository.AuthorRepository;
import com.egg.web.library.service.AuthorService;
import java .util.regex.*;
import java.text.MessageFormat;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
       public  void crearAutor(@RequestBody String nombre){
       Pattern pat = Pattern.compile("[a-zA-Z]");
       nombre = nombre.replaceAll("\"","");
       Matcher mat = pat.matcher(nombre);
       //if(mat.matches())
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
        mav.addObject("id", id);
        return mav;
    }


    @PutMapping(value = "/modificar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void modificar(@RequestBody String json){
       JSONObject myJson = new JSONObject(json);
       Author unAutor = getFooById(myJson.get("id").toString());
       unAutor.setName(myJson.get("nombre").toString());
       aservice.crearAuthor(unAutor);
    }


    @GetMapping("/obtenerAutor")
    public Author getFooById(@RequestParam String id) {
        return  aservice.lookForId(id);
    }
    
}
