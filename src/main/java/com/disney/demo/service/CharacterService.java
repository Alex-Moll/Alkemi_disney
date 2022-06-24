package com.disney.demo.service;

import com.disney.demo.dto.CharacterDto;
import java.util.List;

public interface CharacterService {
    
    CharacterDto saveDto(CharacterDto dto);
    
    CharacterDto find(String id);
    
    List<CharacterDto> findAll();
    
    List<CharacterDto> getByFilters(String name, String date, List<String> movieId);
    
    void delete(String id);
       
}