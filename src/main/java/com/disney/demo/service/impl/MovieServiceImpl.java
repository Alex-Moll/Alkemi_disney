package com.disney.demo.service.impl;

import com.disney.demo.dto.MovieBasicDto;
import com.disney.demo.dto.MovieDto;
import com.disney.demo.dto.MovieFilterDto;
import com.disney.demo.entity.GenderEntity;
import com.disney.demo.entity.MovieEntity;
import com.disney.demo.exception.ParamNotFound;
import com.disney.demo.mapper.MovieMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.disney.demo.repository.MovieRepository;
import com.disney.demo.repository.specification.MovieSpecification;
import com.disney.demo.service.MovieService;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieSpecification movieSpecification;
    
    public MovieDto saveDto(MovieDto dto) {
        MovieEntity movie = new MovieEntity();
//        System.out.println("\n* Entrada dto.character " + dto.getCharacters().toString());
        movie = movieMapper.MovieDto2Movie(dto);
//        System.out.println("\n* movie.character : " + movie.getCharacters().toString() );
        MovieEntity movieGuardar = movieRepository.save(movie);
//        System.out.println("\n* movieGuardar.character : " + movieGuardar.getCharacters().toString());
        dto = movieMapper.Movie2MovieDto(movieGuardar, true);
//        System.out.println("\n* Salida dto.character : " +  dto.getCharacters().toString() );
        return dto;
    }

    @Override
    public MovieDto find(Long id) {
        Optional<MovieEntity> movieDto = movieRepository.findById(id);
        if(!movieDto.isPresent()){
            throw new ParamNotFound("Movie Not Exist");
        }
        MovieDto dto = movieMapper.Movie2MovieDto(movieDto.get(), true);
        return dto;
    }

    @Override
    public List<MovieBasicDto> findAll() {
        List<MovieEntity> movies = movieRepository.findAll();
        List<MovieBasicDto> dtos = movieMapper.listMovie2ListMovieBasicDto(movies, true);
        return dtos;
    }

    @Override
    public void delete(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public List<MovieBasicDto> getByFilters(String title, Long idGender, String order) {

        MovieFilterDto filterDto = new MovieFilterDto(title, idGender, order);
        List<MovieEntity> movie = movieRepository.findAll(this.movieSpecification.getByFilters(filterDto));
        List<MovieBasicDto> dtos = this.movieMapper.listMovie2ListMovieBasicDto(movie, true);
        return dtos;
        
    }
    
    
  
}
