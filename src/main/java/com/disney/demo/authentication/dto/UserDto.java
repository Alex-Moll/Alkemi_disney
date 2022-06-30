package com.disney.demo.authentication.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @Email(message = "Usermail must be an username")
    private String username;
    @Size(min = 8)
    private String password;
    
}
