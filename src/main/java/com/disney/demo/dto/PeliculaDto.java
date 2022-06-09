package com.disney.demo.dto;

import com.disney.demo.entity.Personaje;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class PeliculaDto {
    
    private String id;
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private Integer calificacion;
    private Set<Personaje> personajes = new HashSet<>();

}
