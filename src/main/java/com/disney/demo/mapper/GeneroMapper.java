package com.disney.demo.mapper;

import com.disney.demo.dto.GeneroDto;
import com.disney.demo.entity.Genero;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {
    
    /**
     * llega un Dto, creo un objeto(entity), seteo algunos datos y 
     * retorno esa entidad
     * @param dto
     * @return 
     */
    public Genero generoDto2Genero(GeneroDto dto){
        Genero genero = new Genero();
        genero.setNombre(dto.getNombre());
        genero.setImagen(dto.getImagen());
        return genero;
    }
    
    /**
     * llega una (entity), creo un Dto, seteo las propiedades con id incluido
     * y retorno un Dto
     * @param genero
     * @return 
     */
    public GeneroDto genero2GeneroDto(Genero genero){
        GeneroDto dto = new GeneroDto();
        dto.setId(genero.getId());
        dto.setNombre(genero.getNombre());
        dto.setImagen(genero.getImagen());
        return dto;
    }
        
    /**
     * llega una lista de (entity), creo una lista de Dto, luego
     * cargo en esa lista los entity qie recorro con el for y devuelvo
     * una lista de Dto
     * @param listAll
     * @return 
     */
    public List<GeneroDto> listAllGenero(List<Genero> listAll){
        List<GeneroDto> listDto = new ArrayList<>();
        for (Genero genero : listAll) {
            listDto.add(this.genero2GeneroDto(genero));
        }
        return listDto;
    }
}
