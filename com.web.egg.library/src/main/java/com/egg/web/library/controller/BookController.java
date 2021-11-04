
package com.egg.web.library.controller;


import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.service.AuthorService;
import com.egg.web.library.service.BookService;
import com.egg.web.library.service.EditorialService;
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
    @Autowired
    AuthorService aservice;
     @Autowired
    EditorialService eservice;
    
    @GetMapping()
    public ModelAndView MostrarLibro() {
        ModelAndView mav = new ModelAndView("tablesBook");
        mav.addObject("libros", bservice.findAll());
        mav.addObject("href", "registrar");
        mav.addObject("title", "o Libro");
        mav.addObject("title1", "Libros"); 
        return mav;
    }

    @GetMapping("/registrar")
    public ModelAndView registrarLibro() {
        ModelAndView mav = new ModelAndView("registerBook");
        mav.addObject("autores",aservice.findAll() );
        mav.addObject("editoriales",eservice.findAll() );
        mav.addObject("action", "crear");
        
        return mav;
    }
    @GetMapping("/modificarLibro/{id}")
    public ModelAndView modificarLibro(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("modifyBook");   
         mav.addObject("title1", "Cambios en Libros");
        mav.addObject("objeto", bservice.lookForId(id));
        mav.addObject("autores",aservice.findAll() );
        mav.addObject("editoriales",eservice.findAll() );
        mav.addObject("action", "guardarCambios");

        return mav;
    }

    @GetMapping("/alta/{id}")
    public RedirectView alta(@PathVariable String id) {
        bservice.changeState(id, Boolean.TRUE);
        return new RedirectView("/libros");
    }

    @GetMapping("/baja/{id}")
    public RedirectView baja(@PathVariable String id) {
        bservice.changeState(id, Boolean.FALSE);
        return new RedirectView("/libros");
    }

    @PostMapping("/crear")
    public RedirectView crearAutor(@RequestParam Long isbn, @RequestParam String title,@RequestParam Integer year,@RequestParam Integer copies,@RequestParam String idNameA , @RequestParam String idNameE) throws MyExceptionService {
       bservice.createBook(isbn, title, year, copies, idNameA, idNameE);
        return new RedirectView("/libros");
    }
    @PostMapping("/guardarCambios")
    public RedirectView modificarAuthor(@RequestParam String id,@RequestParam Long isbn, @RequestParam String title,@RequestParam Integer year,@RequestParam Integer copies,@RequestParam String idNameA , @RequestParam String idNameE) throws MyExceptionService  {
        bservice.modifyBook(id, isbn, title, year, copies, idNameA, idNameE);
              
        return new RedirectView("/libros");
    }
}
