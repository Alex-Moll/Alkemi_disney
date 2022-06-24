package com.disney.demo.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterFiltersDto {

    private String name;
    private String date;
    private List<String> movieId;
    
    public CharacterFiltersDto(String name, String date, List<String> movieId){
        this.name = name;
        this.date = date;
        this.movieId = movieId;
    }

    
}
