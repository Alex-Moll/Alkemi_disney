package com.disney.demo.authentication.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDto {
    
    private String jwt;

    public AuthenticationResponseDto(String jwt) {
        this.jwt = jwt;
    }
    
}
