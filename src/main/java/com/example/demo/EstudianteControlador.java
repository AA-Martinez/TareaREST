package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
class EstudianteControlador {

    private final EstudienteRepositorio repositorio;

    EstudianteControlador(EstudienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping(value = "/estudiantes", produces="application/json; charset=UTF-8"
    )
    Resources<Resource<Estudiante>> all(){

        List<Resource<Estudiante>> estudiantes = repositorio.findAll().stream()
                .map(estudiante -> new Resource<>(estudiante,
                        linkTo(methodOn(EstudianteControlador.class).one(estudiante.getId())).withSelfRel(),
                        linkTo(methodOn(EstudianteControlador.class).all()).withRel("estudiantes")))
                .collect(Collectors.toList());

        return new Resources<>(estudiantes,
                linkTo(methodOn(EstudianteControlador.class).all()).withSelfRel());
    }

    @PostMapping("/estudiantes")
    Estudiante estudianteNuevo(@RequestBody Estudiante estudianteNuevo){
        return repositorio.save(estudianteNuevo);
    }

    @GetMapping(value = "/estudiantes/{id}", produces="application/json; charset=UTF-8"
    )
    Resource<Estudiante> one(@PathVariable Long id){

        Estudiante estudiante = repositorio.findById(id)
                .orElseThrow(() -> new EstudianteNotFoundException(id));

        return new Resource<>(estudiante,
                linkTo(methodOn(EstudianteControlador.class).one(id)).withSelfRel(),
                linkTo(methodOn(EstudianteControlador.class).all()).withRel("estudiantes"));
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
