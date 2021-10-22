package com.egg.web.library.exception;

public class MyExceptionService extends Exception {

    public MyExceptionService() {
    }

    public MyExceptionService(String ms) {
        super(ms);
    }

    public static MyExceptionService nameAuthor() {

        return new MyExceptionService("El nombre del autor es obligatorio");
    }

    public static MyExceptionService nameEditorial() {

        return new MyExceptionService("El nombre de la editorial es obligatorio");
    }
     public static MyExceptionService idNotFound() {

        return new MyExceptionService("El id ingresado no se encontro");
    
    
     }
     public static MyExceptionService titleBook () {

        return new MyExceptionService("El titulo del libro es obligatorio");
    }
     public static MyExceptionService isbn () {

        return new MyExceptionService("El isbn del libro es obligatorio");
    }
     public static MyExceptionService copies () {

        return new MyExceptionService("La cantidad de copias del libro es obligatorio");
    }
     }
