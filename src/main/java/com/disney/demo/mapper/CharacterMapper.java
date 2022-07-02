package com.disney.demo.mapper;

import com.disney.demo.dto.CharacterDto;
import com.disney.demo.dto.MovieDto;
import com.disney.demo.entity.CharacterEntity;
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
    
    public CharacterEntity characterDto2Character(CharacterDto dto){
        CharacterEntity character = new CharacterEntity();
        character.setId(dto.getId());
        character.setAge(dto.getAge());
        character.setHistory(dto.getHistory());
        character.setImage(dto.getImage());
        character.setName(dto.getName());
//        character.setPeliculaId(dto.getPeliculaId());
        character.setWeigth(dto.getWeigth());
        
        return character;
    }
    
    public CharacterDto character2CharacterDto(CharacterEntity character, boolean loadMovie){
        CharacterDto dto = new CharacterDto();
        dto.setId(character.getId());
        dto.setImage(character.getImage());
        dto.setName(character.getName());
        dto.setAge(character.getAge());
        dto.setHistory(character.getHistory());
//        dto.setPeliculaId(character.getPeliculaId());
        dto.setWeigth(character.getWeigth());
        if(loadMovie){
            List<MovieDto> dtos = this.movieMapper.listMovie2ListMovieDto(character.getMovies(), false);
            dto.setMovies(dtos);
        }
        return dto;
    }
    
    public List<CharacterDto> listCharacter2ListCharacterDto(List<CharacterEntity> characters, boolean loadMovies){
        List<CharacterDto> dtos = new ArrayList<>();
        for (CharacterEntity character : characters) {
            dtos.add(this.character2CharacterDto(character, false));
        }
        return dtos;
    }
    
    public List<CharacterEntity> listCharacterDto2ListCharacter(List<CharacterDto> charactersDto){
        List<CharacterEntity> characters = new ArrayList<>();
        for (CharacterDto character : charactersDto) {
            characters.add(this.characterDto2Character(character));
        }
        return characters;
    }
    
    

    public CharacterDto optional2CharacterDto(Optional<CharacterEntity> character, boolean loadMovie) {
        CharacterDto dto = new CharacterDto();
        dto.setImage(character.get().getImage());
        dto.setName(character.get().getName());
        dto.setAge(character.get().getAge());
        if(loadMovie){
            dto.setMovies(movieMapper.listMovie2ListMovieDto(character.get().getMovies(), false));
        }
        return dto;
    }
        
}
