package com.disney.demo.controller;

import com.disney.demo.dto.PersonajeDto;
import com.disney.demo.entity.Personaje;
import com.disney.demo.mapper.PersonajeMapper;
import com.disney.demo.service.PersonajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personajes")
public class PersonajeController {
    
    @Autowired
    private PersonajeService personajeService;
    
    @Autowired
    private PersonajeMapper personajeMapper;
    
    @PostMapping("/save")
    public ResponseEntity<PersonajeDto> save(@RequestBody PersonajeDto dto){
        PersonajeDto personajeDto = personajeService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeDto);      
    } 
    
    @GetMapping("/find/{id}")
    public ResponseEntity<PersonajeDto> find(@PathVariable("id") String id){
        PersonajeDto dto = personajeService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<PersonajeDto>> findAll(){
        List<PersonajeDto> dtos = personajeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        this.personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
