package com.disney.demo.mapper;

import com.disney.demo.dto.CharacterDto;
import com.disney.demo.dto.MovieDto;
import com.disney.demo.entity.Character;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    
    @Autowired
    private MovieMapper movieMapper;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    public Character characterDto2Character(CharacterDto dto){
        Character character = new Character();
        character.setId(dto.getId());
        character.setEdad(dto.getEdad());
        character.setHistoria(dto.getHistoria());
        character.setImagen(dto.getImagen());
        character.setNombre(dto.getNombre());
//        character.setPeliculaId(dto.getPeliculaId());
        character.setPeso(dto.getPeso());
        
        return character;
    }
    
    public CharacterDto character2CharacterDto(Character character, boolean loadMovie){
        CharacterDto dto = new CharacterDto();
        dto.setId(character.getId());
        dto.setImagen(character.getImagen());
        dto.setNombre(character.getNombre());
        dto.setEdad(character.getEdad());
        dto.setHistoria(character.getHistoria());
//        dto.setPeliculaId(character.getPeliculaId());
        dto.setPeso(character.getPeso());
        if(loadMovie){
            List<MovieDto> dtos = this.movieMapper.listMovie2ListMovieDto(character.getPeliculas(), false);
            dto.setPeliculas(dtos);
        }
        return dto;
    }
    
    public List<CharacterDto> listCharacter2ListCharacterDto(List<Character> characters, boolean loadMovies){
        List<CharacterDto> dtos = new ArrayList<>();
        for (Character character : characters) {
            dtos.add(this.character2CharacterDto(character, false));
        }
        return dtos;
    }
    
    public List<Character> listCharacterDto2ListCharacter(List<CharacterDto> charactersDto){
        List<Character> characters = new ArrayList<>();
        for (CharacterDto character : charactersDto) {
            characters.add(this.characterDto2Character(character));
        }
        return characters;
    }
    
    

    public CharacterDto optional2CharacterDto(Optional<Character> character, boolean loadMovie) {
        CharacterDto dto = new CharacterDto();
        dto.setImagen(character.get().getImagen());
        dto.setNombre(character.get().getNombre());
        dto.setEdad(character.get().getEdad());
        if(loadMovie){
            dto.setPeliculas(movieMapper.listMovie2ListMovieDto(character.get().getPeliculas(), false));
        }
        return dto;
    }
        
}
