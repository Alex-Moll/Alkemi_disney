package com.disney.demo.dto;

import lombok.Data;

@Data
public class MovieFilterDto {
    
    private String title;
    private Long idGender;
    private String order;

    public MovieFilterDto(String title, Long idGender, String order) {
        this.title = title;
        this.idGender = idGender;
        this.order = order;
    }
    
    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
