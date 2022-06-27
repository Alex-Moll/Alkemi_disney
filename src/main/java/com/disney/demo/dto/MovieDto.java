package com.disney.demo.dto;

import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class MovieDto {
    
    private long id;
    private String imagen;
    @NotNull
    private String titulo;
    private String fechaCreacion;
    private GeneroDto genero;
    @Range(min=1, max=5)
    private Integer calificacion;
    private List<CharacterDto> personajes = new ArrayList<>();
    private boolean deleted;
    
    
    public MovieDto() {
        
    }

    
}
