package com.disney.demo.service.impl;

import com.disney.demo.dto.MovieDto;
import com.disney.demo.entity.Movie;
import com.disney.demo.exception.ParamNotFound;
import com.disney.demo.mapper.MovieMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.disney.demo.repository.MovieRepository;
import com.disney.demo.service.MovieService;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    
    public MovieDto saveDto(MovieDto dto) {
        Movie movie = new Movie();
        System.out.println("\n* Entrada dto.character " + dto.getPersonajes().toString());
        movie = movieMapper.MovieDto2Movie(dto);
        System.out.println("\n* movie.character : " + movie.getPersonajes().toString() );
        Movie movieGuardar = movieRepository.save(movie);
        System.out.println("\n* movieGuardar.character : " + movieGuardar.getPersonajes().toString());
        dto = movieMapper.Movie2MovieDto(movieGuardar, true);
        System.out.println("\n* Salida dto.character : " +  dto.getPersonajes().toString() );
        return dto;
    }

    @Override
    public MovieDto find(long id) {
        Optional<Movie> movieDto = movieRepository.findById(id);
        if(!movieDto.isPresent()){
            throw new ParamNotFound("no existe esta Pelicula");
        }
        MovieDto dto = movieMapper.Movie2MovieDto(movieDto.get(), true);
        return dto;
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDto> dtos = movieMapper.listMovie2ListMovieDto(movies, false);
        return dtos;
    }

    @Override
    public void delete(long id) {
        this.movieRepository.deleteById(id);
    }
    
    
  
}
