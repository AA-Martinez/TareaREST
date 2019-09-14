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
    private String apellido;
    private int numeroCelular;

    Estudiante(){}

    public Estudiante(String nombre, String apellido, int numeroCelular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroCelular = numeroCelular;
    }

    public String obtenerNombre(){
        return this.nombre + " " + this.apellido;
    }

    public void agregarNombre(String nombre){
        String[] partes = nombre.split(" ");
        this.nombre = partes[0];
        this.apellido = partes[1];
    }

}
