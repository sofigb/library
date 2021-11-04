package com.egg.web.library.controller;

import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.service.CustomerService;
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
@RequestMapping("/socios")
public class CustomerController {

    @Autowired
    CustomerService cservice;

    @GetMapping()
    public ModelAndView MostrarClientes() {
        ModelAndView mav = new ModelAndView("tablesCustomer");
        mav.addObject("socios", cservice.findAll());
        mav.addObject("href", "registrar");
        mav.addObject("title", "o Socio");
        mav.addObject("title1", "Socios"); 
        return mav;
    }

    @GetMapping("/registrar")
    public ModelAndView registrarLibro() {
        ModelAndView mav = new ModelAndView("registerCustomer");
        mav.addObject("action", "crear");

        return mav;
    }

    @GetMapping("/modificarSocios/{id}")
    public ModelAndView modificarLibro(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("modifyCustomer");
        mav.addObject("title1", "Cambios en Clientes");
        mav.addObject("objeto", cservice.lookForId(id));
        mav.addObject("action", "guardarCambios");
        return mav;
    }

    @GetMapping("/alta/{id}")
    public RedirectView alta(@PathVariable String id) {
        cservice.changeState(id, Boolean.TRUE);
        return new RedirectView("/socios");
    }

    @GetMapping("/baja/{id}")
    public RedirectView baja(@PathVariable String id) {
        cservice.changeState(id, Boolean.FALSE);
        return new RedirectView("/socios");
    }

    @PostMapping("/crear")
    public RedirectView crearSocio(@RequestParam Long dni, @RequestParam String name, @RequestParam String surname, @RequestParam String phone) throws MyExceptionService {
        cservice.createCustomer(dni, name, surname, phone);
        return new RedirectView("/socios");
    }

    @PostMapping("/guardarCambios")
    public RedirectView modificarSocio(@RequestParam String id, @RequestParam Long dni, @RequestParam String name, @RequestParam String surname, @RequestParam String phone) throws MyExceptionService {

        cservice.modifyCustomer(id, dni, name, surname, phone);

        return new RedirectView("/socios");
    }
}
