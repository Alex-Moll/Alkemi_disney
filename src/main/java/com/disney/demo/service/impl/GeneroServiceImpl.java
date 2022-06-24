package com.disney.demo.service.impl;

import com.disney.demo.dto.GeneroDto;
import com.disney.demo.entity.Genero;
import com.disney.demo.exception.ParamNotFound;
import com.disney.demo.mapper.GeneroMapper;
import com.disney.demo.repository.GeneroRepository;
import com.disney.demo.service.GeneroService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;
    
    @Autowired
    private GeneroRepository generoRepository;
    
    public GeneroDto saveDto(GeneroDto dto){  
        // convierto un dto en entity creada
        Genero genero = generoMapper.generoDto2Genero(dto);
        //guardo la entity en generoSave obteniendo el id
        Genero generoSave = generoRepository.save(genero);
        // luego convierto esa entidadSave en dto y retorno eso
        GeneroDto result = generoMapper.genero2GeneroDto(generoSave);
        return result;
    }
    
    @Override
    public GeneroDto find(String id) {
//        Genero genero = generoRepository.findById(id).orElse(null);
        Optional<Genero> genero = generoRepository.findById(id);
        if(!genero.isPresent()){
            throw new ParamNotFound("no existe ese genero");
        }
        GeneroDto dto = generoMapper.genero2GeneroDto(genero.get());
        return dto;
    }
    
    @Override
    public List<GeneroDto> findAll() {
        List<Genero> generos = generoRepository.findAll();
        List<GeneroDto> result = generoMapper.listAll2GeneroDto(generos);
        return result;
    }
    
    @Override
    public void delete(String id) {
        this.generoRepository.deleteById(id);
    }
   

}
