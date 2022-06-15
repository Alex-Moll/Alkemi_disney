package com.disney.demo.mapper;

import com.disney.demo.dto.PeliculaDto;
import com.disney.demo.entity.Pelicula;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PeliculaMapper {

    public Pelicula PeliculaDto2Pelicula(PeliculaDto dto) {

        Pelicula pelicula = new Pelicula();
        pelicula.setCalificacion(dto.getCalificacion());
        pelicula.setFechaCreacion(dto.getFechaCreacion());
        pelicula.setImagen(dto.getImagen());
        pelicula.setPersonajes(dto.getPersonajes());
        pelicula.setTitulo(dto.getTitulo());
        return pelicula;
    }

    public PeliculaDto Pelicula2PeliculaDto(Pelicula pelicula) {

        PeliculaDto dto = new PeliculaDto();
        dto.setCalificacion(pelicula.getCalificacion());
        dto.setFechaCreacion(pelicula.getFechaCreacion());
        dto.setId(pelicula.getId());
        dto.setImagen(pelicula.getImagen());
        dto.setPersonajes(pelicula.getPersonajes());
        dto.setTitulo(pelicula.getTitulo());
        return dto;
    }

    public List<PeliculaDto> getAll(List<Pelicula> peliculas) {
        List<PeliculaDto> dtos = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            dtos.add(this.Pelicula2PeliculaDto(pelicula));
        }
        return dtos;
    }

}
