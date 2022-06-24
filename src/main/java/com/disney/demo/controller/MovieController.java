package com.disney.demo.controller;

import com.disney.demo.dto.MovieDto;
import com.disney.demo.mapper.MovieMapper;
import com.disney.demo.mapper.CharacterMapper;
import com.disney.demo.service.impl.GeneroServiceImpl;
import java.util.List;
import javax.validation.Valid;
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
import com.disney.demo.repository.MovieRepository;
import com.disney.demo.service.MovieService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("movies")
public class MovieController {
    
    @Autowired
    private MovieMapper peliculaMapper;
    
    @Autowired
    private CharacterMapper personajeMapper;
    
    @Autowired
    private MovieService peliculaService;
    
    @Autowired
    private MovieRepository peliculaRepository;
    
    @Autowired
    private GeneroServiceImpl generoServiceImpl; 
    
    @PostMapping()
    public ResponseEntity<MovieDto> save(@Valid @RequestBody MovieDto dto){
        System.out.println("\nentro peliculas/save");
        System.out.println("dto : " + dto);
        System.out.println("dto.getGeneroDto() : " + dto.getGenero());
        System.out.println("dto.getPersonajes() : " + dto.getPersonajes());
        MovieDto movieDto = peliculaService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> find(@PathVariable("id") String id){
        System.out.println("\nentro peliculas/find");
        MovieDto dto = peliculaService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    @GetMapping()
    public ResponseEntity<List <MovieDto>> findAll(){
        System.out.println("\nentro peliculas/findAll");
        List<MovieDto> dtos = peliculaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        System.out.println("\nentro peliculas/dalete");
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
