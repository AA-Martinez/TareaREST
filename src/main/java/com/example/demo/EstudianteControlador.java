package com.example.demo;

import java.lang.management.LockInfo;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EstudianteControlador {

    private final EstudienteRepositorio repositorio;

    public EstudianteControlador(EstudienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/estudiantes")
    List<Estudiante> all(){
        return repositorio.findAll();
    }

    @PostMapping("/estudiantes")
    Estudiante estudianteNuevo(@RequestBody Estudiante estudianteNuevo){
        return repositorio.save(estudianteNuevo);
    }

    @GetMapping("/estudiantes/{id}")
    Estudiante uno(@PathVariable Long id){
        return repositorio.findById(id)
                .orElseThrow(() -> new EstudianteNotFoundException(id));
    }

    @PutMapping("/estudiante/{id}")
    Estudiante remplazarEstudiante(@RequestBody Estudiante estudianteNuevo, @PathVariable Long id){
        return repositorio.findById(id)
                .map(estudiante -> {
                    estudiante.setNombre(estudianteNuevo.getNombre());
                    estudiante.setNumeroCelular(estudianteNuevo.getNumeroCelular());
                    return repositorio.save(estudiante);
                })
                .orElseGet(() -> {
                    estudianteNuevo.setId(id);
                    return repositorio.save(estudianteNuevo);
                });
    }

    @DeleteMapping("/estudiante/{id}")
    void borrarEstudiante(@PathVariable Long id){
        repositorio.deleteById(id);
    }





}
