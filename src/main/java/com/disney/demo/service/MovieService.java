package com.disney.demo.service;

import com.disney.demo.dto.MovieBasicDto;
import com.disney.demo.dto.MovieDto;
import java.util.List;

public interface MovieService {
    
    MovieDto saveDto(MovieDto dto);
    
    MovieDto find(Long id);
    
    List<MovieBasicDto> findAll();
    
    void delete(Long id);
    
}
