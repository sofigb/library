
package com.egg.web.library.controller;
import com.egg.web.library.entity.Author;
import com.egg.web.library.entity.Editorial;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.service.EditorialService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class EditorialController {
    @Autowired
    EditorialService eService;


    @GetMapping(value="/listarEditoriales",produces = {MediaType.APPLICATION_JSON_VALUE})
    private List <Editorial> listaEditoriales() {
        List <Editorial> editoriales = eService.findAll();
        return editoriales;
    }



    // revisar el tryCatch y poner un mensaje que se creo el editor

    @PostMapping(value="/crearEditorial",produces = {MediaType.APPLICATION_JSON_VALUE})
    public  String  CrearEditorial(@RequestBody String nombre) throws MyExceptionService {
        JSONObject myJson = new JSONObject(nombre);
        String  nombreEditorial = myJson.get("name").toString();
        eService.CrearEditorial(nombreEditorial);
        myJson = new JSONObject();
        myJson.put("message","se  creo  la editorial de nombre "+nombreEditorial);
        myJson.put("status","OK");
        return myJson.toString();
    }

    @PutMapping(value = "/modificarEditorial", consumes = {MediaType.APPLICATION_JSON_VALUE} , produces = {MediaType.APPLICATION_JSON_VALUE})
    public String ModificarEditorial(@RequestBody String json){
        JSONObject myJson = new JSONObject(json);
        Editorial unEditor = eService.lookForId(myJson.get("id").toString());
        unEditor.setName(myJson.get("name").toString());
        eService.CrearEditorial(unEditor);
        myJson = new JSONObject();
        myJson.put("message","se  modifico el editorial con exito");
        myJson.put("status","OK");
        return myJson.toString();

    }

    @GetMapping(value="/altaEditorial",produces = MediaType.APPLICATION_JSON_VALUE)
    private String AltaEditorial(@RequestParam String id)  {
        JSONObject myJson = new JSONObject();
        eService.changeState(id,Boolean.TRUE);
        myJson.put("message","La editorial  ha sido dada de alta");
        myJson.put("status","OK");
        return myJson.toString();
    }


    @GetMapping(value="/bajaEditorial", produces = MediaType.APPLICATION_JSON_VALUE)
    private String BajaEditorial(@RequestParam String id) {
        JSONObject myJson = new JSONObject();
        eService.changeState(id,Boolean.FALSE);
        myJson.put("message","La editorial  ha sido dada de baja");
        myJson.put("status","OK");
        return myJson.toString();
    }



    /*

    @GetMapping("/mostrarAutores")
    public ModelAndView MostrarAuthor() {
        ModelAndView mav = new ModelAndView("listarAuthor");
        mav.addObject("title", "listarAuthor");
        mav.addObject("autores", listaAuthor());
        return mav;
    }



    @GetMapping("/altaAutor")
    private String altaAutor(@RequestParam String id) throws MyExceptionService {
        aservice.changeState(id, Boolean.TRUE);
        String mensaje = "El autor " + aservice.lookForId(id).getName() +" ha sido dado de alta";
        final String json = "{\"mensaje\":\" "+mensaje+" \",\"status\":\""+Boolean.TRUE+"\"}";
        return json;
    }

    @GetMapping("/bajaAutor")
    private String bajaAutor(@RequestParam String id) throws MyExceptionService {
        aservice.changeState(id, Boolean.FALSE);
        String mensaje = "El autor " + aservice.lookForId(id).getName() +" ha sido dado de baja";
        final String json = "{\"mensaje\":\" "+mensaje+" \",\"status\":\""+Boolean.FALSE+"\"}";
        return json;
    }*/





    /*

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

    */

    
}
