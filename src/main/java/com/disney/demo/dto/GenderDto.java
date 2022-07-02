package com.disney.demo.dto;

import lombok.Data;

@Data
public class GenderDto {
    
    private long id;
    private String name;
    private String image;
    private boolean deleted;
    
}
