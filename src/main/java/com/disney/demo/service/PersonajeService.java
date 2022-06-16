package com.disney.demo.service;

import com.disney.demo.dto.PersonajeDto;
import java.util.List;

public interface PersonajeService {
    
    PersonajeDto saveDto(PersonajeDto dto);
    
    PersonajeDto find(String id);
    
    List<PersonajeDto> findAll();
    
    void delete(String id);
       
}