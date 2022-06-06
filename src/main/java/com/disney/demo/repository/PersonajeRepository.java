package com.disney.demo.repository;

import com.disney.demo.entity.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, String>{
    
    public List<Personaje> findByNombre();
    
}
