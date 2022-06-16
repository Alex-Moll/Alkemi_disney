package com.disney.demo.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class PeliculaDto {
    
    private String id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private String genero;
    private Integer calificacion;
    private Set<PersonajeDto> personajes = new HashSet<>();

}
