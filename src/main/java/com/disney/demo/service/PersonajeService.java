package com.disney.demo.service;

import com.disney.demo.dto.PersonajeDto;
import java.util.List;

public interface PersonajeService {
    
    PersonajeDto saveDto(PersonajeDto dto);
    
    PersonajeDto get(PersonajeDto dto);
    
    List<PersonajeDto> getAll();
    
    void delete(PersonajeDto dto);
       
}