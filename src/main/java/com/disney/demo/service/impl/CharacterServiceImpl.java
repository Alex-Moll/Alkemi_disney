package com.disney.demo.service.impl;

import com.disney.demo.dto.CharacterDto;
import com.disney.demo.dto.CharacterFiltersDto;
import com.disney.demo.entity.Character;
import com.disney.demo.exception.ParamNotFound;
import com.disney.demo.mapper.CharacterMapper;
import com.disney.demo.repository.CharacterRepository;
import com.disney.demo.repository.specification.CharacterSpecification;
import com.disney.demo.service.CharacterService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
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
    
    @Transactional
    public CharacterDto saveDto(CharacterDto dto){
        Character character = characterMapper.characterDto2Character(dto);
        Character characterGuardado = characterRepository.save(character);
        dto = characterMapper.character2CharacterDto(characterGuardado, false);
        return dto;
    }
    
    @Transactional
    @Override
    public List<CharacterDto> findAll() {
        List<Character> characters = characterRepository.findAll();
        List<CharacterDto> dtos = characterMapper.listCharacter2ListCharacterDto(characters, false);
        return dtos;
    }

    @Transactional
    @Override
    public CharacterDto find(long id) {
        CharacterDto dto = characterService.find(id);
        return dto;
    }
    
    @Transactional
    @Override
    public List<CharacterDto> getByFilters(String name, Integer age, List<Long> movies, String order) {
    
        CharacterFiltersDto filterDto = new CharacterFiltersDto(name, age, movies, order);
        
        List<Character> characters = this.characterRepository.findAll(this.characterSpecification.getByFilters(filterDto));
        
        List<CharacterDto> dtos = this.characterMapper.listCharacter2ListCharacterDto(characters, true);
        
        return dtos;
        
    }
        
//    @Override
//    public List<CharacterBasicDTO> getByFilters(String name, Integer age, Double weight,
//                                        List<Long> movieList, String order) {
//        
//         CharacterFiltersDTO filterDTO = new CharacterFiltersDTO(name, age, weight, movieList, order);
//        
//        List<CharacterEntity> characterList= this.characterRepository.findAll(this.characterSpecification.getByFilters(filterDTO));
//        
//        List<CharacterBasicDTO> characterBasicDTOList = this.characterMapper.characterEntityList2BasicDTOList(characterList);
//               
//        return characterBasicDTOList;
//        
//    }
    
    @Transactional
    @Override
    public CharacterDto getDetailsById(Long id) {
        Optional<Character> character = characterRepository.findById(id);
        if(!character.isPresent()){
            throw new ParamNotFound ("No exist the Character by Id");
        }
        CharacterDto dto = characterMapper.optional2CharacterDto(character, true);
        return dto;
    }
    
    @Override
    public void delete(long id) {
        if(!this.characterRepository.findById(id).isPresent()){
            throw new ParamNotFound("no exist the character for remove");
        }
    }

   

               
}
