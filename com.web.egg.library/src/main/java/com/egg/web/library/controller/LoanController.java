package com.egg.web.library.controller;

import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.service.BookService;
import com.egg.web.library.service.CustomerService;
import com.egg.web.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/prestamos")
public class LoanController {

    @Autowired
    CustomerService cService;
    @Autowired
    LoanService lService;
    @Autowired
    BookService bService;

    @GetMapping()
    public ModelAndView MostrarPrestamos() {
        ModelAndView mav = new ModelAndView("tablesLoan");
        mav.addObject("prestamos", lService.findAll());
        mav.addObject("href", "registrar");
        mav.addObject("title", "o Prestamo");
        mav.addObject("title1", "Prestamo");
        return mav;
    }

    @GetMapping("/registrar")
    public ModelAndView registrarPrestamo() {
        ModelAndView mav = new ModelAndView("registerLoan");
        mav.addObject("libros", bService.bookInStock());
        mav.addObject("socios", cService.notBussy());
        mav.addObject("action", "crear");

        return mav;
    }
    
    @GetMapping("/modificar/{id}")
    public ModelAndView modificarLibro(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("modifyLoan");   
         mav.addObject("title1", "Cambios en Prestamo");
         mav.addObject("objeto", lService.lookForId(id));
         mav.addObject("libros", bService.bookInStock());
        mav.addObject("socios", cService.notBussy());
       mav.addObject("action", "guardarCambios");

        return mav;
    }
//    @GetMapping("/baja/{id}")
//    public RedirectView baja(@PathVariable String id) {
//        lService.changeState(id, Boolean.FALSE);
//        return new RedirectView("/libros");
//    }
    
    @PostMapping("/crear")
    public RedirectView crearPrestamo( @RequestParam String idBook, @RequestParam String idCustomer) throws MyExceptionService {
      lService.createLoad(idBook, idCustomer);
        return new RedirectView("/prestamos");
    }
    
    @PostMapping("/guardarCambios")
    public RedirectView modificarAuthor(@RequestParam String id,@RequestParam String idBook, @RequestParam String idCustomer)   {
        lService.modifyLoad(id, idBook, idCustomer);
              
        return new RedirectView("/prestamos");
    }
}
