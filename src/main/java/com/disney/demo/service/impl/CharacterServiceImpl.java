package com.disney.demo.service.impl;

import com.disney.demo.dto.CharacterDto;
import com.disney.demo.dto.CharacterFiltersDto;
import com.disney.demo.entity.Character;
import com.disney.demo.exception.ParamNotFound;
import com.disney.demo.mapper.CharacterMapper;
import com.disney.demo.repository.CharacterRepository;
import com.disney.demo.repository.specification.CharacterSpecification;
import com.disney.demo.service.CharacterService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CharacterServiceImpl implements CharacterService{
    
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterSpecification characterSpecification;
    
    
    public CharacterDto saveDto(CharacterDto dto){
        Character character = characterMapper.characterDto2Character(dto);
        Character characterGuardado = characterRepository.save(character);
        dto = characterMapper.character2CharacterDto(characterGuardado, false);
        return dto;
    }
    
    @Override
    public List<CharacterDto> findAll() {
        List<Character> characters = characterRepository.findAll();
        List<CharacterDto> dtos = characterMapper.listCharacter2ListCharacterDto(characters, false);
        return dtos;
    }

    @Override
    public CharacterDto find(long id) {
        CharacterDto dto = characterService.find(id);
        return dto;
    }
    
//    @Override
//    public List<CharacterDto> getByFilters(String name, String date, List<Long> movies, String order) {
//        CharacterFiltersDto filtersDto = new CharacterFiltersDto(name, date, movies, order);
////        List<Character> characters = new ArrayList<>();
////        characters = characterRepository.findAll(characterSpecification.getByFilters(filtersDto));
////        List<CharacterDto> dtos = characterMapper.listCharacter2ListCharacterDto(characters, true);
////        List<CharacterDto> dtos = new ArrayList<>();
//        return dtos; 
//    }
    
//    @Override
//    public List<CharacterDto> getByFilters(String name, String date, List<Long> movies, String order) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
//    @Override
//    public CharacterDto getDetailsById(Long id) {
//        Optional<Character> character = characterRepository.findById(id);
//        if(character.isPresent()){
//            throw new ParamNotFound ("El tgtgtgrtgr");
//        }
//        
//    }
    
    public CharacterDto getDetailsById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(long id) {
        this.characterRepository.findById(id);
    }

    public List<CharacterDto> getByFilters(String name, String date, List<Long> movies, String order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   

    

       
}
