package com.disney.demo.mapper;

import com.disney.demo.dto.PersonajeDto;
import com.disney.demo.entity.Personaje;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersonajeMapper {
    
    public Personaje personajeDto2Personaje(PersonajeDto dto){
        Personaje personaje = new Personaje();
        personaje.setFechaNac(string2LocalDate(dto.getFechaNac()));
        personaje.setHistoria(dto.getHistoria());
        personaje.setImagen(dto.getImagen());
        personaje.setNombre(dto.getNombre());
        personaje.setPeliculaId(dto.getPeliculaId());
//        personaje.setPeliculas(dto.getPeliculas());
        personaje.setPeso(dto.getPeso());
        return personaje;
    }
    
    public PersonajeDto personaje2PersonajeDto(Personaje personaje){
        PersonajeDto dto = new PersonajeDto();
        dto.setFechaNac(localDate2String(personaje.getFechaNac()));
        dto.setHistoria(personaje.getHistoria());
        dto.setId(personaje.getId());
        dto.setImagen(personaje.getImagen());
        dto.setNombre(personaje.getNombre());
        dto.setPeliculaId(personaje.getPeliculaId());
//        dto.setPeliculas(personaje.getPeliculas());
        dto.setPeso(personaje.getPeso());
        return dto;
    }
    
    public List<PersonajeDto> getAll(List<Personaje> personajes){
        List<PersonajeDto> dtos = new ArrayList<>();
        for (Personaje personaje : personajes) {
            dtos.add(this.personaje2PersonajeDto(personaje));
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
