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

    public void save(Genero genero) {
        generoRepository.save(genero);
    }

    public Genero find(String id) {
        try{
            return generoRepository.findById(id).orElse(null);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
        
    }

    public List<Genero> findAll() {       
        try{
            return generoRepository.findAll();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void delete(String id) {
        try{
            generoRepository.deleteById(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(GeneroDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
