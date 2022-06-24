package com.disney.demo.service.impl;

import com.disney.demo.dto.CharacterDto;
import com.disney.demo.dto.CharacterFiltersDto;
import com.disney.demo.entity.Character;
import com.disney.demo.mapper.CharacterMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.disney.demo.repository.CharacterRepository;
import com.disney.demo.repository.specification.CharacterSpecificacion;
import com.disney.demo.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService{
    
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterSpecificacion characterSpecification;
    
    
    public CharacterDto saveDto(CharacterDto dto){
        // convierto un dto en entity
        Character character = characterMapper.characterDto2Character(dto);
        //guardo la entity con characterSave obteniendo el id
        Character characterGuardado = characterRepository.save(character);
        // luego convierto esa entidadSave en dto y retorno eso
        dto = characterMapper.character2CharacterDto(characterGuardado);
        return dto;
    }
    
    @Override
    public List<CharacterDto> findAll() {
        List<Character> characters = characterRepository.findAll();
        List<CharacterDto> dtos = characterMapper.listCharacter2ListCharacterDto(characters, false);
        return dtos;
    }

    @Override
    public CharacterDto find(String id) {
        CharacterDto dto = characterService.find(id);
        return dto;
    }
    
//    @Override
//    public List<CharacterDto> getByFilters(String name, String date, List<String> movieId) {
//        CharacterFiltersDto filters = new CharacterFiltersDto(name, date, movieId);
////        List<Character> characters = this.characterRepository.findAll(characterSpecification.getByFilters(filters));
////        List<CharacterDto> dtos = characterMapper.getAllDto(characters, true);
//        return dtos; 
//    }

    @Override
    public void delete(String id) {
        this.characterRepository.deleteById(id);
    }

    @Override
    public List<CharacterDto> getByFilters(String name, String date, List<String> movieId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
