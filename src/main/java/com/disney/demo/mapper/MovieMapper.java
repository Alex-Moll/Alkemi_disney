package com.disney.demo.mapper;

import com.disney.demo.dto.MovieDto;
import com.disney.demo.dto.CharacterDto;
import com.disney.demo.entity.Movie;
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
    private GeneroMapper generoMapper;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    public Movie MovieDto2Movie(MovieDto dto) {

        Movie movie = new Movie();
        
        movie.setImagen(dto.getImagen());
        movie.setTitulo(dto.getTitulo());
        LocalDate date = LocalDate.parse(dto.getFechaCreacion(), formatter);
        movie.setFechaCreacion(date);
        movie.setCalificacion(dto.getCalificacion());
        movie.setGenero(generoMapper.generoDto2Genero(dto.getGenero()));
        movie.setPersonajes(characterMapper.listCharacterDto2ListCharacter(dto.getPersonajes()));
        return movie;
        
    }
    
    public MovieDto Movie2MovieDto(Movie movie,  boolean loadCharacter) {

        MovieDto dto = new MovieDto();
        
        dto.setId(movie.getId());
        dto.setImagen(movie.getImagen());
        dto.setTitulo(movie.getTitulo());
        String date = movie.getFechaCreacion().format(formatter);
        dto.setFechaCreacion(date);
        dto.setCalificacion(movie.getCalificacion());
        dto.setGenero(generoMapper.genero2GeneroDto(movie.getGenero()));
        dto.setPersonajes(characterMapper.listCharacter2ListCharacterDto(movie.getPersonajes(), false));
        if(loadCharacter){
            List<CharacterDto> dtos = this.characterMapper.listCharacter2ListCharacterDto(movie.getPersonajes(), false);
            dto.setPersonajes(dtos);
        }
        return dto;
        
    }

    public List<MovieDto> listMovie2ListMovieDto(List<Movie> movies, boolean loadCharacter) {
        List<MovieDto> dtos = new ArrayList<>();
        for (Movie movie : movies) {
            dtos.add(this.Movie2MovieDto(movie, loadCharacter));
        }
        return dtos;
    }
    
}
