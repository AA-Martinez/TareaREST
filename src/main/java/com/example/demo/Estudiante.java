package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Estudiante {
    private @Id @GeneratedValue Long id;
    private String nombre;
    private int numeroCelular;

    Estudiante(){}

    public Estudiante(String nombre, int numeroCelular) {
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
    }
}
