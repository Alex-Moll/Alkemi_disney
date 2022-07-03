package com.disney.demo.service.impl;

import com.disney.demo.dto.GenderDto;
import com.disney.demo.entity.GenderEntity;
import com.disney.demo.exception.ParamNotFound;
import com.disney.demo.mapper.GenderMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.disney.demo.repository.GenderRepository;
import com.disney.demo.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderMapper generoMapper;
    
    @Autowired
    private GenderRepository generoRepository;
    
    public GenderDto saveDto(GenderDto dto){  
        // convierto un dto en entity creada
        GenderEntity genero = generoMapper.generoDto2Genero(dto);
        //guardo la entity en generoSave obteniendo el id
        GenderEntity generoSave = generoRepository.save(genero);
        // luego convierto esa entidadSave en dto y retorno eso
        GenderDto result = generoMapper.genero2GeneroDto(generoSave);
        return result;
    }
    
    @Override
    public GenderDto find(long id) {
//        GenderEntity genero = generoRepository.findById(id).orElse(null);
        Optional<GenderEntity> genero = generoRepository.findById(id);
        
        if(!genero.isPresent()){
            throw new ParamNotFound("Gender Not Exist");
        }
        GenderDto dto = generoMapper.genero2GeneroDto(genero.get());
        return dto;
    }
    
    @Override
    public List<GenderDto> findAll() {
        List<GenderEntity> generos = generoRepository.findAll();
        List<GenderDto> result = generoMapper.listAll2GeneroDto(generos);
        return result;
    }
    
    @Override
    public void delete(long id) {
        this.generoRepository.deleteById(id);
    }
   

}
