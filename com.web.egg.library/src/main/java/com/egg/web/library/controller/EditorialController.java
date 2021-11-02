
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
import org.springframework.web.servlet.ModelAndView;


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


    @GetMapping(value="/listarEditoriales2",produces = {MediaType.APPLICATION_JSON_VALUE})
    private List <Editorial> listaEditorialesSp() {
        List <Editorial> editoriales = eService.obtenerEditoriales();
        return editoriales;
    }

    @GetMapping(value="/altaEditorialSp",produces = MediaType.APPLICATION_JSON_VALUE)
    private String ActivarEditorialSP(@RequestParam String id)  {
        JSONObject myJson = new JSONObject();
        String mensaje = eService.activarEditorial(id);
        if(mensaje.equals("OK")){
            myJson.put("message","La editorial  ha sido activado");
            myJson.put("descripcion","Desactivar");
            myJson.put("status","true");
        }else{
            myJson.put("message", mensaje);
            myJson.put("descripcion","Error");
            myJson.put("status","Error");
        }
        return myJson.toString();
    }



    @GetMapping(value="/desactivarEditorialSp",produces = MediaType.APPLICATION_JSON_VALUE)
    private String DesactivarEditorialSP(@RequestParam String id)  {
        JSONObject myJson = new JSONObject();
        String mensaje = eService.desactivarEditorial(id);
        if(mensaje.equals("OK")){
            myJson.put("message","La editorial  ha sido dada desactivado");
            myJson.put("descripcion","Activar");
            myJson.put("status","false");
        }else{
            myJson.put("message",mensaje);
            myJson.put("descripcion","Error");
            myJson.put("status","Error");
        }
        return myJson.toString();
    }







    @GetMapping("/mostrarEditoriales")
    public ModelAndView MostrarEditoriales() {
        ModelAndView mav = new ModelAndView("listarEditorial");
        mav.addObject("title", "listarEditoriales");
        mav.addObject("editoriales", listaEditoriales());
        return mav;
    }


    @GetMapping("/crearEditorial")
    public ModelAndView CrearEditorial() {
        ModelAndView mav = new ModelAndView("crearEditorial");
        mav.addObject("title", "CrearEditorial");
        return mav;
    }



    // revisar el tryCatch y poner un mensaje que se creo el editor

    @PostMapping(value="/crearEditorial",produces = {MediaType.APPLICATION_JSON_VALUE})
    public  String  CrearEditorial(@RequestBody String editorial) throws MyExceptionService {
        JSONObject myJson = new JSONObject(editorial);
        String  nombreEditorial = myJson.get("nombre").toString();
        eService.CrearEditorial(nombreEditorial);
        myJson = new JSONObject();
        myJson.put("message","se  creo  la editorial de nombre "+nombreEditorial);
        myJson.put("status","OK");
        return myJson.toString();
    }




    @GetMapping("/modificarEditorial")
    public ModelAndView modificarEditorial(@RequestParam String id) {
        Editorial editorial = eService.lookForId(id);
        ModelAndView mav = new ModelAndView("modificarEditorial");
        mav.addObject("title", "modificarEdiorial");
        mav.addObject("editorial", editorial);
        return mav;
    }


    @PutMapping(value = "/modificaEditorial", consumes = {MediaType.APPLICATION_JSON_VALUE} , produces = {MediaType.APPLICATION_JSON_VALUE})
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


    
}
