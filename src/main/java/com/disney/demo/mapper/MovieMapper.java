package com.disney.demo.mapper;

import com.disney.demo.dto.MovieDto;
import com.disney.demo.dto.CharacterDto;
import com.disney.demo.entity.MovieEntity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
        
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private GenderMapper generoMapper;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    public MovieEntity MovieDto2Movie(MovieDto dto) {

        MovieEntity movie = new MovieEntity();
        
        movie.setImage(dto.getImage());
        movie.setTitle(dto.getTitle());
        LocalDate date = LocalDate.parse(dto.getCreationDate(), formatter);
        movie.setCreationDate(date);
        movie.setCalification(dto.getCalification());
        movie.setGender(generoMapper.generoDto2Genero(dto.getGender()));
        movie.setCharacters(characterMapper.listCharacterDto2ListCharacter(dto.getCharacters()));
        return movie;
        
    }
    
    public MovieDto Movie2MovieDto(MovieEntity movie,  boolean loadCharacter) {

        MovieDto dto = new MovieDto();
        
        dto.setId(movie.getId());
        dto.setImage(movie.getImage());
        dto.setTitle(movie.getTitle());
        String date = movie.getCreationDate().format(formatter);
        dto.setCreationDate(date);
        dto.setCalification(movie.getCalification());
        dto.setGender(generoMapper.genero2GeneroDto(movie.getGender()));
//        dto.setCharacters(characterMapper.listCharacter2ListCharacterDto(movie.getCharacters(), false));
        if(loadCharacter){
            List<CharacterDto> dtos = this.characterMapper.listCharacter2ListCharacterDto(movie.getCharacters(), false);
            dto.setCharacters(dtos);
        }
        return dto;
        
    }

    public List<MovieDto> listMovie2ListMovieDto(List<MovieEntity> movies, boolean loadCharacter) {
        List<MovieDto> dtos = new ArrayList<>();
        for (MovieEntity movie : movies) {
            dtos.add(this.Movie2MovieDto(movie, true));
        }
        return dtos;
    }
    
}
