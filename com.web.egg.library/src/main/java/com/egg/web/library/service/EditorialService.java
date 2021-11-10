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
    public void crearEditorial(String name) throws MyExceptionService {

        Validation.validationName(name);

        Editorial editorial = new Editorial();
        editorial.setName(name);
        editorial.setStatus(true);
        editorialRep.save(editorial);
    }

    @Transactional
    public Editorial returnEditorial(String idname) {

        if (editorialRep.findById(idname).isPresent()) {

            return (editorialRep.findById(idname).get());
        } else {

            Editorial editorial = new Editorial();
            editorial.setName(idname);
            editorial.setStatus(true);

            return (editorialRep.save(editorial));
        }
    }

//NO ESTOY USANDO MI QUERY ESPECIAL PARA ESTO
    @Transactional
    public void modifyName(String id, String name) throws MyExceptionService {
//        try {
//            Validation.validationService(name);
//            Optional<Editorial> reponse = editorialRep.findById(id);
//            Validation.validationIDfound(id, reponse);
//
//        } catch (MyExceptionService e) {
//            throw new MyExceptionService();
//        }

        Editorial editorial = editorialRep.findById(id).get();
        editorial.setName(name);
        editorialRep.save(editorial);

    }

    @Transactional
    public void changeState(String id, Boolean status) {
//        try {
//            Optional<Author> reponse = authorRep.findById(id);
//            Validation.validationIDfound(id, reponse);
//
//        } catch (MyExceptionService e) {
//            throw new MyExceptionService();
//        }

        editorialRep.changeStatus(id, status);
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
}
