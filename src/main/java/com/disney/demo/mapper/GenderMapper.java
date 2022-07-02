package com.disney.demo.mapper;

import com.disney.demo.dto.GenderDto;
import com.disney.demo.entity.GenderEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper {
    
    /**
     * llega un Dto, creo un objeto(entity), seteo algunos datos y 
     * retorno esa entidad
     * @param dto
     * @return 
     */
    public GenderEntity generoDto2Genero(GenderDto dto){
        GenderEntity genero = new GenderEntity();
        genero.setId(dto.getId());
        genero.setName(dto.getName());
        genero.setImage(dto.getImage());
        return genero;
    }
    
    /**
     * llega una (entity), creo un Dto, seteo las propiedades con id incluido
     * y retorno un Dto
     * @param genero
     * @return 
     */
    public GenderDto genero2GeneroDto(GenderEntity genero){
        GenderDto dto = new GenderDto();
        dto.setId(genero.getId());
        dto.setName(genero.getName());
        dto.setImage(genero.getImage());
        return dto;
    }
        
    /**
     * llega una lista de (entity), creo una lista de Dto, luego
     * cargo en esa lista los entity que recorro con el for y devuelvo
     * una lista de Dto
     * @param listAll
     * @return 
     */
    public List<GenderDto> listAll2GeneroDto(List<GenderEntity> listAll){
        List<GenderDto> listDto = new ArrayList<>();
        for (GenderEntity genero : listAll) {
            // obtengo un genero, luego con el this voy y lo convierto en dto
            // para cargarlo a la lista de dtos
            listDto.add(this.genero2GeneroDto(genero));
        }
        return listDto;
    }
    
}
