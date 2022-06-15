package com.disney.demo.service.impl;

import com.disney.demo.dto.GeneroDto;
import com.disney.demo.entity.Genero;
import com.disney.demo.mapper.GeneroMapper;
import com.disney.demo.repository.GeneroRepository;
import com.disney.demo.service.GeneroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;
    
    @Autowired
    private GeneroRepository generoRepository;
    
    public GeneroDto saveDto(GeneroDto dto){  
        // convierto un dto en entity
        Genero genero = generoMapper.generoDto2Genero(dto);
        //guardo la entity con generoSave obteniendo el id
        Genero generoSave = generoRepository.save(genero);
        // luego convierto esa entidadSave en dto y retorno eso
        GeneroDto result = generoMapper.genero2GeneroDto(generoSave);
        return result;
    }
    
    @Override
    public List<GeneroDto> getAll() {
        List<Genero> genero = generoRepository.findAll();
        List<GeneroDto> result = generoMapper.listAllGenero(genero);
        return result;
    }
    
    @Override
    public void delete(GeneroDto dto) {
        Genero genero = new Genero();
        genero = generoMapper.generoDto2Genero(dto);
        genero.isDeleted();
//        generoRepository.delete(genero);  
    }

}
