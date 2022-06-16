package com.disney.demo.service.impl;

import com.disney.demo.dto.PersonajeDto;
import com.disney.demo.entity.Personaje;
import com.disney.demo.mapper.PersonajeMapper;
import com.disney.demo.repository.PersonajeRepository;
import com.disney.demo.service.PersonajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImpl implements PersonajeService{
    
    @Autowired
    private PersonajeService personajeService;
    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepository personajeRepository;
    
    public PersonajeDto saveDto(PersonajeDto dto){
        // convierto un dto en entity
        Personaje personaje = personajeMapper.personajeDto2Personaje(dto);
        //guardo la entity con personajeSave obteniendo el id
        Personaje personajeGuardado = personajeRepository.save(personaje);
        // luego convierto esa entidadSave en dto y retorno eso
        dto = personajeMapper.personaje2PersonajeDto(personajeGuardado);
        return dto;
    }
    
    @Override
    public List<PersonajeDto> findAll() {
        List<Personaje> personajes = personajeRepository.findAll();
        List<PersonajeDto> dtos = personajeMapper.getAll(personajes);
        return dtos;
    }

    @Override
    public PersonajeDto find(String id) {
        PersonajeDto dto = personajeService.find(id);
        return dto;
    }

    @Override
    public void delete(String id) {
        this.personajeRepository.deleteById(id);
    }
    
}
