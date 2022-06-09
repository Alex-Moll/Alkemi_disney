package com.disney.demo.service;

import com.disney.demo.entity.Pelicula;
import com.disney.demo.repository.PeliculaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {
    
    @Autowired 
    private PeliculaRepository peliculaRepository;
    
    public void save(Pelicula pelicula){
        peliculaRepository.save(pelicula);
    }

    public Pelicula find(String id){       
        try{
            return peliculaRepository.findById(id).orElse(null);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public List<Pelicula> finddAll(){       
        try{
            return peliculaRepository.findAll();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public void delete(String id){
        try{
            peliculaRepository.deleteById(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
