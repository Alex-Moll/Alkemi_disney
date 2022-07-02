package com.disney.demo.authentication.controller;

import com.disney.demo.authentication.dto.AuthenticationRequestDto;
import com.disney.demo.authentication.dto.AuthenticationResponseDto;
import com.disney.demo.authentication.dto.UserDto;
import com.disney.demo.authentication.service.JwtUtils;
import com.disney.demo.authentication.service.UserDetailsCustomService;
import com.disney.demo.exception.ErrorEnum;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    
    //aqui se haria la registracion
    @PostMapping("/singup")
    public ResponseEntity<AuthenticationResponseDto> singup(@Valid @RequestBody UserDto userDto) throws Exception{
        this.userDetailsCustomService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    // aqui seria el inicio de secion y generacion del token
    @PostMapping("/singin")
    public ResponseEntity<AuthenticationResponseDto> singin(@RequestBody AuthenticationRequestDto authRequest) throws Exception{
        
        UserDetails userDetails;
        
        try{
            Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            userDetails = (UserDetails) auth.getPrincipal();
        }catch (BadCredentialsException bce){
            throw new Exception(ErrorEnum.BADCREDENTIAL.getMessage() + bce);
        }
        
        final String jwt = jwtUtils.generatedToken(userDetails);
        
        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }
    
    
    
}
