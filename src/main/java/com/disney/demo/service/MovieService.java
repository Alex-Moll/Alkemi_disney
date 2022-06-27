package com.disney.demo.service;

import com.disney.demo.dto.MovieDto;
import java.util.List;

public interface MovieService {
    
    MovieDto saveDto(MovieDto dto);
    
    MovieDto find(long id);
    
    List<MovieDto> findAll();
    
    void delete(long id);
    
}
