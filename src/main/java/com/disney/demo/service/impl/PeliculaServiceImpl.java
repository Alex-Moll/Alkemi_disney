package com.disney.demo.service.impl;

import com.disney.demo.dto.PeliculaDto;
import com.disney.demo.entity.Pelicula;
import com.disney.demo.mapper.PeliculaMapper;
import com.disney.demo.repository.PeliculaRepository;
import com.disney.demo.service.PeliculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PeliculaServiceImpl implements PeliculaService{

    @Autowired
    private PeliculaRepository peliculaRepository;
    
    @Autowired
    private PeliculaMapper peliculaMapper;
       
    @Override
    public PeliculaDto saveDto(PeliculaDto dto) {
        Pelicula pelicula = new Pelicula();
        pelicula = peliculaMapper.PeliculaDto2Pelicula(dto);
        Pelicula peliculaGuardar = peliculaRepository.save(pelicula);
        dto = peliculaMapper.Pelicula2PeliculaDto(peliculaGuardar);
        return dto;
    }

    @Override
    public PeliculaDto find(String id) {
        Pelicula pelicula = peliculaRepository.findById(id).orElse(null);
        PeliculaDto dto = peliculaMapper.Pelicula2PeliculaDto(pelicula);
        return dto;
    }

    @Override
    public List<PeliculaDto> findAll() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        List<PeliculaDto> dtos = peliculaMapper.getAll(peliculas);
        return dtos;
    }

    @Override
    public void delete(String id) {
        this.peliculaRepository.deleteById(id);
    }

   
}
