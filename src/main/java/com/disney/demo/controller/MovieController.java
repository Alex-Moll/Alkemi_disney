package com.disney.demo.controller;

import com.disney.demo.dto.MovieBasicDto;
import com.disney.demo.dto.MovieDto;
import com.disney.demo.entity.GenderEntity;
import com.disney.demo.mapper.MovieMapper;
import com.disney.demo.mapper.CharacterMapper;
import com.disney.demo.service.impl.GenderServiceImpl;
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
    
//    @Autowired
//    private MovieMapper movieMapper;   
    @Autowired
    private MovieService movieService;    
    @Autowired
    private MovieRepository movieRepository;   
    
    @PostMapping()
    public ResponseEntity<MovieDto> save(@Valid @RequestBody MovieDto dto){
        System.out.println("\nentro peliculas/save");
        System.out.println("dto : " + dto.toString());
        System.out.println("dto.getGeneroDto() : " + dto.getGender().toString());
        System.out.println("dto.getPersonajes() : " + dto.getCharacters().toString());
        MovieDto movieDto = movieService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> find(@PathVariable("id") long id){
        System.out.println("\nentro peliculas/find");
        MovieDto dto = movieService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List <MovieBasicDto>> findAll(){
        System.out.println("\nentro peliculas/findAll");
        List<MovieBasicDto> dtos = movieService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        System.out.println("\nentro peliculas/dalete");
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @GetMapping
    public ResponseEntity<List<MovieBasicDto>> getMovieBasicByFilters ( @Valid
            @RequestParam (required = false) String title,
            @RequestParam (required = false) Long idGender,
            @RequestParam (required = false, defaultValue = "ASC") String order){
        
        List<MovieBasicDto> dtos = movieService.getByFilters(title, idGender, order);
        
        return ResponseEntity.ok().body(dtos);
    } 
    
}
