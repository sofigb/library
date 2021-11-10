package com.egg.web.library.exception;

public class MyExceptionService extends Exception {

    public MyExceptionService() {
    }

    public MyExceptionService(String ms) {
        super(ms);
    }

    public static MyExceptionService name() {

        return new MyExceptionService("El nombre  es obligatorio");
    }

    public static MyExceptionService nameFormat() {

        return new MyExceptionService("El campo solo puede contener Letras");
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
