package com.disney.demo.service.impl;

import com.disney.demo.dto.MovieDto;
import com.disney.demo.entity.Movie;
import com.disney.demo.mapper.MovieMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.disney.demo.repository.MovieRepository;
import com.disney.demo.service.MovieService;


@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private MovieMapper movieMapper;
       
    @Override
    public MovieDto saveDto(MovieDto dto) {
        Movie movie = new Movie();
        movie = movieMapper.MovieDto2Movie(dto);
        Movie movieGuardar = movieRepository.save(movie);
        dto = movieMapper.Movie2MovieDto(movieGuardar, true);
        return dto;
    }

    @Override
    public MovieDto find(String id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        MovieDto dto = movieMapper.Movie2MovieDto(movie, true);
        return dto;
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDto> dtos = movieMapper.listMovie2ListMovieDto(movies, false);
        return dtos;
    }

    @Override
    public void delete(String id) {
        this.movieRepository.deleteById(id);
    }
  
}
