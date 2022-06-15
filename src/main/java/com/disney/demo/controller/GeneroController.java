package com.disney.demo.controller;

import com.disney.demo.dto.GeneroDto;
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
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("generos")
public class GeneroController {
    
    @Autowired
    private GeneroService generoService;
    
    @PostMapping("/save")
    public ResponseEntity<GeneroDto> save(@RequestBody GeneroDto dto){
        GeneroDto generoGuardado = generoService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);      
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<GeneroDto>> getAll(){
        List<GeneroDto> listDto = generoService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }
    
    @PutMapping("/update")
    public ResponseEntity<GeneroDto> update(@RequestBody GeneroDto dto){
        GeneroDto generoGuardado = generoService.saveDto(dto);
        return ResponseEntity.status(HttpStatus.OK).body(generoGuardado); 
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody GeneroDto dto){
        generoService.delete(dto);
        return new ResponseEntity<>("xxxxx",HttpStatus.NO_CONTENT); 
    }
    
}
