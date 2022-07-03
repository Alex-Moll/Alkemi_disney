package com.disney.demo.controller;

import com.disney.demo.dto.CharacterDto;
import com.disney.demo.mapper.CharacterMapper;
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
import com.disney.demo.service.CharacterService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("characters")
public class CharacterController {
    
    @Autowired
    private CharacterService characterService;
    
    @Autowired
    private CharacterMapper characterMapper;
    
    @PostMapping()
    public ResponseEntity<CharacterDto> save(@Valid @RequestBody CharacterDto dto){
        System.out.println("\nentro personajes/save");
        CharacterDto characterDto = characterService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterDto);      
    } 
    
    @PutMapping()
    public ResponseEntity<CharacterDto> update(@Valid @RequestBody CharacterDto dto){
        System.out.println("\nentro a characters/update");
        this.characterService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<CharacterDto>> findAll(){
        System.out.println("\nentro personajes/findAll");
        List<CharacterDto> dtos = characterService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<CharacterDto> find(@PathVariable Long id){
        System.out.println("\nentro personajes/find");
        CharacterDto dto = characterService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
           
    @DeleteMapping ("/{id}")
    public ResponseEntity <CharacterDto> delete (@PathVariable Long id){ 
        System.out.println("\nentro personajes/delete");
        this.characterService.delete(id);        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @GetMapping
    public ResponseEntity<List<CharacterDto>> getDetailsByFilters ( @Valid
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) List<Long> movies,
            @RequestParam (required = false, defaultValue = "ASC") String order){
        
        List<CharacterDto> dtos = characterService.getByFilters(name, age, movies, order);
        
        return ResponseEntity.ok().body(dtos);
    } 
    
    
}
