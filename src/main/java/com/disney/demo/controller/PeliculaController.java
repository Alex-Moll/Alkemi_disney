package com.disney.demo.controller;

import com.disney.demo.dto.PeliculaDto;
import com.disney.demo.entity.Pelicula;
import com.disney.demo.mapper.PeliculaMapper;
import com.disney.demo.repository.PeliculaRepository;
import com.disney.demo.service.PeliculaService;
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
@RequestMapping("peliculas")
public class PeliculaController {
    
    @Autowired
    private PeliculaMapper peliculaMapper;
    
    @Autowired
    private PeliculaService peliculaService;
    
    @Autowired
    private PeliculaRepository peliculaRepository;
    
    @PostMapping("/save")
    public ResponseEntity<PeliculaDto> save(@RequestBody PeliculaDto dto){
        PeliculaDto peliculaDto = peliculaService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaDto);
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<PeliculaDto> find(@PathVariable("id") String id){
        PeliculaDto dto = peliculaService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List <PeliculaDto>> findAll(){
        List<PeliculaDto> dtos = peliculaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
