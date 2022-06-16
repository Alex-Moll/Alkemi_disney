package com.disney.demo.controller;

import com.disney.demo.dto.GeneroDto;
import com.disney.demo.entity.Genero;
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
    
    @PostMapping("/save")
    public ResponseEntity<GeneroDto> save(@RequestBody GeneroDto dto){
        GeneroDto dtoGuardado = generoService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoGuardado);      
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<GeneroDto>> findAll(){
        List<GeneroDto> listDto = generoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }
    
    @PutMapping("/update")
    public ResponseEntity<GeneroDto> update(@RequestBody GeneroDto dto){
        GeneroDto generoGuardado = generoService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.OK).body(generoGuardado); 
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        this.generoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
   
}
