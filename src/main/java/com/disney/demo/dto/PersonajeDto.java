package com.disney.demo.dto;

import com.disney.demo.entity.Pelicula;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PersonajeDto {
    
    private String id;
    private String imagen;
    private String nombre;
    private LocalDate fechaNac;
    private double peso;
    private String historia;
    private List<Pelicula> peliculas = new ArrayList<>();
    private String peliculaId;

}
