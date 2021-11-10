package com.egg.web.library.validation;

import com.egg.web.library.entity.Author;
import com.egg.web.library.entity.Editorial;
import com.egg.web.library.exception.MyExceptionService;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static void validationName(String name) throws MyExceptionService {
        if (name == null || name.isEmpty()) {
            throw MyExceptionService.name();
        }

        Pattern patterString = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)*$");
        Matcher matcherName = patterString.matcher(name);
        if (!matcherName.matches()) {
            throw MyExceptionService.nameFormat();
        }
    }

    public static void validationIDfound(String id, Optional reponse) throws MyExceptionService {

        if (!reponse.isPresent()) {
            throw MyExceptionService.idNotFound();
        }

    }

    public static void validationService(Long isbn, String title, Integer year, Integer copies) throws MyExceptionService {

        if (title == null || title.isEmpty()) {
            throw MyExceptionService.titleBook();
        }
        if (isbn == null) {
            throw MyExceptionService.name();
        }
        if (year == null) {
            throw MyExceptionService.isbn();
        }
        if (copies == null) {
            throw MyExceptionService.copies();
        }

    }
}
