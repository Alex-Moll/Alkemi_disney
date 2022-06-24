package com.disney.demo.dto;

import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CharacterDto {
    
    private String id;
    private String imagen;
    @NotNull
    private String nombre;
    private int edad;
    private double peso;
    private String historia;
    private List<MovieDto> peliculas = new ArrayList<>();
//    private String peliculaId;
    private boolean deleted;
    
    public CharacterDto(){
        
    }
}
