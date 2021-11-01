package com.egg.web.library.service;

import com.egg.web.library.entity.Editorial;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.repository.EditorialRepository;
import com.egg.web.library.validation.Validation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRep;

    @Transactional
    public void CrearEditorial(String name) throws MyExceptionService {
        try {
            Validation.validationService(name);
        } catch (MyExceptionService e) {
            throw MyExceptionService.nameAuthor();
        }
        Editorial editorial = new Editorial();
        editorial.setName(name);
        editorial.setStatus(true);
        editorialRep.save(editorial);
    }

    @Transactional
    public void CrearEditorial(Editorial ed){
        editorialRep.save(ed);
    }



    @Transactional
    public void changeState(String id, Boolean status) {
        Editorial editorial = editorialRep.findById(id).get();
        editorial.setStatus(status);
        editorialRep.save(editorial);

    }




//NO ESTOY USANDO MI QUERY ESPECIAL PARA ESTO

    @Transactional
    public void modifyName(String id, String name) throws MyExceptionService {
        try {
            Validation.validationService(name);
            Optional<Editorial> reponse = editorialRep.findById(id);
            Validation.validationIDfound(id, reponse);

        } catch (MyExceptionService e) {
            throw new MyExceptionService();
        }

        Editorial editorial = editorialRep.findById(id).get();
        editorial.setName(name);
        editorialRep.save(editorial);

    }

    @Transactional
    public void unsuscribe(String id) throws MyExceptionService {
        try {
            Optional<Editorial> reponse = editorialRep.findById(id);
            Validation.validationIDfound(id, reponse);

        } catch (MyExceptionService e) {
            throw new MyExceptionService();
        }

        Editorial editorial = editorialRep.findById(id).get();
        editorial.setStatus(false);
        editorialRep.save(editorial);
    }

    @Transactional(readOnly = true)
    public Editorial lookForId(String id) {
        Optional<Editorial> editorialOptional = editorialRep.findById(id);
        return editorialOptional.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Editorial> findAll() {
        return editorialRep.findAll();
    }

    @Transactional(readOnly = true)
    public List<Editorial> obtenerEditoriales() {
        return editorialRep.listarEditoriales();
    }

    @Transactional(readOnly = false)
    public String activarEditorial(String id) {
       return editorialRep.activarEditorial(id);
    }

    @Transactional(readOnly = false)
    public String desactivarEditorial(String id) {
        return editorialRep.desactivarEditorial(id);
    }



}
