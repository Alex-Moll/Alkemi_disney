package com.disney.demo.service;

import com.disney.demo.dto.CharacterDto;
import java.util.List;

public interface CharacterService {
    
    CharacterDto saveDto(CharacterDto dto);
    
    CharacterDto find(Long id);
    
    CharacterDto update(Long id);
    
    List<CharacterDto> findAll();
    
    List<CharacterDto> getByFilters(String name, Integer age, List<Long> movies, String order);
    
    CharacterDto getDetailsById(Long id);
    
    void delete(Long id);
       
}