package com.disney.demo.service;

import com.disney.demo.dto.PeliculaDto;
import java.util.List;

public interface PeliculaService {
    
    PeliculaDto saveDto(PeliculaDto dto);
    
    PeliculaDto get(PeliculaDto dto);
    
    List<PeliculaDto> getAll();
    
    void delete(PeliculaDto dto);
    
}
