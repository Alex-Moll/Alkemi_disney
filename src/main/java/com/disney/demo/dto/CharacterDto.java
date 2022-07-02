package com.disney.demo.dto;

import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CharacterDto {
    
    private long id;
    private String image;
//    @NotNull
    private String name;
    private int age;
    private double weigth;
    private String history;
    private List<MovieDto> movies = new ArrayList<>();
    private boolean deleted;
    
    public CharacterDto(){
        
    }
}
