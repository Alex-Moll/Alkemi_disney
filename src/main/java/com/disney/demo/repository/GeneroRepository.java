package com.disney.demo.repository;

import com.disney.demo.entity.Genero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, String>{
    
    public List<Genero> findByNombre();
    
}
