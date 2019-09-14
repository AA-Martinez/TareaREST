package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/estudiantes/{id}")
    Estudiante remplazarEEstudiante(@RequestBody Estudiante estudianteNuevo, @PathVariable Long id) {

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

    @DeleteMapping("/estudiantes/{id}")
    void borrarEstudiante(@PathVariable Long id){
        repositorio.deleteById(id);
    }


}
