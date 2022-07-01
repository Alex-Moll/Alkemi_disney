package com.disney.demo.service;

import com.disney.demo.dto.GenderDto;
import java.util.List;

public interface GenderService {

    GenderDto saveDto(GenderDto dto);
    
    GenderDto find(long id);
    
    List<GenderDto> findAll();
    
    void delete(long id);
     
}
