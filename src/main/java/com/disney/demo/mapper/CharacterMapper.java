package com.disney.demo.mapper;

import com.disney.demo.dto.CharacterDto;
import com.disney.demo.entity.Character;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    
    @Autowired
    private MovieMapper peliculaMapper;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    public Character characterDto2Character(CharacterDto dto){
        Character character = new Character();
        character.setEdad(dto.getEdad());
        character.setHistoria(dto.getHistoria());
        character.setImagen(dto.getImagen());
        character.setNombre(dto.getNombre());
//        character.setPeliculaId(dto.getPeliculaId());
        character.setPeso(dto.getPeso());
        return character;
    }
    
    public CharacterDto character2CharacterDto(Character character){
        CharacterDto dto = new CharacterDto();
        dto.setEdad(character.getEdad());
        dto.setHistoria(character.getHistoria());
        dto.setId(character.getId());
        dto.setImagen(character.getImagen());
        dto.setNombre(character.getNombre());
//        dto.setPeliculaId(character.getPeliculaId());
        dto.setPeso(character.getPeso());
        return dto;
    }
    
    public List<CharacterDto> listCharacter2ListCharacterDto(List<Character> characters, boolean loadMovies){
        List<CharacterDto> dtos = new ArrayList<>();
        for (Character character : characters) {
            dtos.add(this.character2CharacterDto(character));
        }
        return dtos;
    }
    
    public List<Character> getAllEntity(List<CharacterDto> charactersDto){
        List<Character> characters = new ArrayList<>();
        for (CharacterDto character : charactersDto) {
            characters.add(this.characterDto2Character(character));
        }
        return characters;
    }
    
//    public LocalDate string2LocalDate(String stringDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDate localDate = LocalDate.parse(stringDate, formatter);
//        return localDate;
//    }
//    
//    public String localDate2String(LocalDate localDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        String stringDate = localDate.format(formatter);
//        return stringDate;
//    }
    
}
