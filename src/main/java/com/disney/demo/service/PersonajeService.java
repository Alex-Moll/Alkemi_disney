package com.disney.demo.service;

import com.disney.demo.entity.Personaje;
import com.disney.demo.repository.PersonajeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeService {
    
    @Autowired
    private PersonajeRepository personajeRepository;
    
    public void guardar(Personaje personaje){
        try{
            personajeRepository.save(personaje);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Personaje buscar(String id){
        try{
            return personajeRepository.findById(id).orElse(null);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
        
    }
    
    public List<Personaje> listar(){       
        try{
            return personajeRepository.findAll();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public void eliminar(String id){
        try{
            personajeRepository.deleteById(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}
