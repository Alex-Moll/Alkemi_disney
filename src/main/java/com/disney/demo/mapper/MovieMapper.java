package com.disney.demo.mapper;

import com.disney.demo.dto.CharacterBasicDto;
import com.disney.demo.dto.MovieDto;
import com.disney.demo.dto.CharacterDto;
import com.disney.demo.dto.MovieBasicDto;
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
        if(loadCharacter){
            List<CharacterDto> dtos = this.characterMapper.listCharacter2ListCharacterDto(movie.getCharacters(), false);
            dto.setCharacters(dtos);
        }
        return dto;
        
    }
    
    
    public MovieBasicDto Movie2MovieBasicDto(MovieEntity movie,  boolean loadCharacter) {

        MovieBasicDto dto = new MovieBasicDto();
        
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        String date = movie.getCreationDate().format(formatter);
        dto.setCreationDate(date);
        dto.setCalification(movie.getCalification());
        dto.setGender(generoMapper.genero2GeneroDto(movie.getGender()));
        if(loadCharacter){
            List<CharacterBasicDto> dtos = this.characterMapper.listCharacter2ListCharacterBasicDto(movie.getCharacters(), false);
            dto.setCharacters(dtos);
        }
        return dto;
        
    }

    public List<MovieDto> listMovie2ListMovieDto(List<MovieEntity> movies, boolean loadCharacter) {
        List<MovieDto> dtos = new ArrayList<>();
        for (MovieEntity movie : movies) {
            dtos.add(this.Movie2MovieDto(movie, loadCharacter));
        }
        return dtos;
    }
    
    /**
     * este metodo retorna una lista - Movies Basic Dto - 
     *                       con sus - Character Basic Dto -
     */
    public List<MovieBasicDto> listMovie2ListMovieBasicDto(List<MovieEntity> movies, boolean loadCharacter) {
        List<MovieBasicDto> dtos = new ArrayList<>();
        for (MovieEntity movie : movies) {
            dtos.add(this.Movie2MovieBasicDto(movie, loadCharacter));
        }
        return dtos;
    }
    
}
