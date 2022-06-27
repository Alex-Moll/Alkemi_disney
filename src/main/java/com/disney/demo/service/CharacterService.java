package com.disney.demo.service;

import com.disney.demo.dto.CharacterDto;
import java.util.List;

public interface CharacterService {
    
    CharacterDto saveDto(CharacterDto dto);
    
    CharacterDto find(long id);
    
    List<CharacterDto> findAll();
    
    List<CharacterDto> getByFilters(String name, String date, List<Long> movies, String order);
    
    CharacterDto getDetailsById(Long id);
    
    void delete(long id);
       
}