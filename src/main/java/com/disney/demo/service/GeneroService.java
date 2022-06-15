package com.disney.demo.service;

import com.disney.demo.dto.GeneroDto;
import java.util.List;

public interface GeneroService {

    GeneroDto saveDto(GeneroDto dto);
    
    List<GeneroDto> getAll();
    
    void delete(GeneroDto dto);
     
}
