package com.example.demo;

public class EstudianteNotFoundException extends RuntimeException {

    EstudianteNotFoundException(Long id){
        super("No se encontro estudiante "+id);
    }

}
