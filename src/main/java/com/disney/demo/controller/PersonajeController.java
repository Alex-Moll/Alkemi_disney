package com.disney.demo.controller;

import com.disney.demo.dto.PersonajeDto;
import com.disney.demo.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personajes")
public class PersonajeController {
    
    @Autowired
    private PersonajeService personajeService;
    
    @PostMapping("/save")
    public ResponseEntity<PersonajeDto> save(@RequestBody PersonajeDto dto){
        PersonajeDto personajeGuardado = personajeService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);      
    } 
    
    
    
}
