package com.egg.web.library.validation;

import com.egg.web.library.entity.Author;
import com.egg.web.library.entity.Editorial;
import com.egg.web.library.exception.MyExceptionService;

import java.util.Optional;

public class Validation {

    public static void validationService(String name) throws MyExceptionService {
        if (name == null || name.isEmpty()) {
            throw  MyExceptionService.nameAuthor();
        }
    }

    public static void validationIDfound(String id, Optional reponse) throws MyExceptionService {

        if (!reponse.isPresent()) {
            throw  MyExceptionService.idNotFound();
        }

    }
    public static void validationService(Long isbn, String title, Integer year, Integer copies) throws MyExceptionService {

        if (title == null || title.isEmpty()) {
            throw  MyExceptionService.titleBook();
        } if (isbn == null ) {
            throw  MyExceptionService.nameAuthor();
        } if (year == null ) {
            throw  MyExceptionService.isbn();
        } if (copies == null ) {
            throw  MyExceptionService.copies();
        }

    }
}
