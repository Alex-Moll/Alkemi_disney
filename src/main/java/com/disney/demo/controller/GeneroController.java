package com.disney.demo.controller;

import com.disney.demo.dto.GeneroDto;
import com.disney.demo.mapper.GeneroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.disney.demo.service.GeneroService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("generos")
public class GeneroController {
    
    @Autowired
    private GeneroService generoService;
    
    @Autowired
    private GeneroMapper generoMapper;
    
    @PostMapping()
    public ResponseEntity<GeneroDto> save(@Valid @RequestBody GeneroDto dto){
        System.out.println("\nentro a generos/save");
        GeneroDto dtoGuardado = generoService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoGuardado);      
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GeneroDto> find(@PathVariable String id){
        System.out.println("\nentro a generos/find/id");
        GeneroDto dto = generoService.find(id);
        
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    @GetMapping()
    public ResponseEntity<List<GeneroDto>> findAll(){
        System.out.println("\nentro a generos/findAll");
        List<GeneroDto> listDto = generoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        System.out.println("\nentro a generos/delete/id");
        this.generoService.delete(id);
        System.out.println("llego a borrar");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<GeneroDto> update(@PathVariable String id){
        System.out.println("\nentro a generos/update/id");
        GeneroDto dto = generoService.find(id);
        GeneroDto generoGuardado = generoService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.OK).body(generoGuardado); 
    }
   
}
