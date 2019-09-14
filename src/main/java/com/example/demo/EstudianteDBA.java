package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class EstudianteDBA {

    @Bean
    CommandLineRunner iniciarBD(EstudienteRepositorio repositorio){
        return args -> {
            repositorio.save(new Estudiante("Alejandro Martinez",73048478));
            repositorio.save(new Estudiante("Cristopher Castro",77743818));
        };
    }

}
