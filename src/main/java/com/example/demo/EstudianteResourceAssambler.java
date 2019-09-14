package com.example.demo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class EstudianteResourceAssambler implements ResourceAssembler<Estudiante, Resource<Estudiante>> {

    @Override
    public Resource<Estudiante> toResource(Estudiante estudiante) {
        return new Resource<>(estudiante,
                linkTo(methodOn(EstudianteControlador.class).one(estudiante.getId())).withSelfRel(),
                linkTo(methodOn(EstudianteControlador.class).all()).withRel("estudiantes"));
    }






}
