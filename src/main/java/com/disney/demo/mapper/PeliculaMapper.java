package com.disney.demo.mapper;

import com.disney.demo.dto.PeliculaDto;
import com.disney.demo.entity.Pelicula;
import com.disney.demo.mapper.util.LocalDate2String;
import com.disney.demo.mapper.util.String2LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeliculaMapper {
    
    public Pelicula PeliculaDto2Pelicula(PeliculaDto dto) {

        Pelicula pelicula = new Pelicula();
        pelicula.setCalificacion(dto.getCalificacion());
        pelicula.setFechaCreacion(string2LocalDate(dto.getFechaCreacion()));
        pelicula.setImagen(dto.getImagen());
//        pelicula.setPersonajes(dto.getPersonajes());
        pelicula.setTitulo(dto.getTitulo());
        return pelicula;
    }

    public PeliculaDto Pelicula2PeliculaDto(Pelicula pelicula) {

        PeliculaDto dto = new PeliculaDto();
        dto.setCalificacion(pelicula.getCalificacion());
        dto.setFechaCreacion(localDate2String(pelicula.getFechaCreacion()));
        dto.setId(pelicula.getId());
        dto.setImagen(pelicula.getImagen());
//        dto.setPersonajes(pelicula.getPersonajes());
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
    
    public LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(stringDate, formatter);
        return localDate;
    }

    public String localDate2String(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String stringDate = localDate.format(formatter);
        return stringDate;
    }
}
