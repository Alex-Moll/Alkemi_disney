package com.disney.demo.dto;

import lombok.Data;

@Data
public class GenderDto {
    
    private long id;
    private String nombre;
    private String imagen;
    private boolean deleted;
    
}
