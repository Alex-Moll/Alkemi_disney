package com.disney.demo.service;

import com.disney.demo.entity.Genero;
import com.disney.demo.repository.GeneroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public void guardar(Genero genero) {
        generoRepository.save(genero);
    }

    public Genero buscar(String id) {
        try{
            return generoRepository.findById(id).orElse(null);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
        
    }

    public List<Genero> listar() {       
        try{
            return generoRepository.findAll();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void eliminar(String id) {
        try{
            generoRepository.deleteById(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
