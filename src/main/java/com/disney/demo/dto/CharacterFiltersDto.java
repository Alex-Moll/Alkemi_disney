package com.disney.demo.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterFiltersDto {

    private String name;
    private String date;
    private List<Long> movies;
    private String order;
    
    public CharacterFiltersDto(String name, String date, List<Long> movies, String order){
        this.name = name;
        this.date = date;
        this.movies = movies;
        this.order = order;
    }
    
    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
    
}
