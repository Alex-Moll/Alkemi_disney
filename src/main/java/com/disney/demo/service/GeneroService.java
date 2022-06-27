package com.disney.demo.service;

import com.disney.demo.dto.GeneroDto;
import java.util.List;

public interface GeneroService {

    GeneroDto saveDto(GeneroDto dto);
    
    GeneroDto find(long id);
    
    List<GeneroDto> findAll();
    
    void delete(long id);
     
}
