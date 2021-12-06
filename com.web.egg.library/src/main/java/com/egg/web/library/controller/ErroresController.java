package com.egg.web.library.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.boot.web.servlet.error.ErrorController;




@RestController

public class ErroresController implements ErrorController {

  @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView errores(HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("error");
        String message;
        int code = response.getStatus();
        
        switch (code) {
            case 403:
                message = "No tienen permisos suficientes para acceder al recurso solicitado";
                 break;
            case 404:
                message = "El recurso solicitado no fue encontrado";
                 break;
            case 500:
                message = "Error interno en el servidor";
                break;
            default:
                message = "Error inesperado";

        }
        mav.addObject("mensaje", message);
        mav.addObject("codigo", code);
        return mav;
    }

}
