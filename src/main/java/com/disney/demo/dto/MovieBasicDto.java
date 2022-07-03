package com.disney.demo.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MovieBasicDto {
    
    private long id;
    private String title;
    private String creationDate;
    private GenderDto gender;
    private Integer calification;
    private List<CharacterBasicDto> characters = new ArrayList<>();
    private boolean deleted;
    
}
