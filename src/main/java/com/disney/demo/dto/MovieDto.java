package com.disney.demo.dto;

import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class MovieDto {
    
    private long id;
    private String image;
    @NotNull
    private String title;
    private String creationDate;
    private GenderDto gender;
    @Range(min=1, max=5)
    private Integer calification;
    private List<CharacterDto> characters = new ArrayList<>();
    private boolean deleted;
    
    
    public MovieDto() {
        
    }

    
}
