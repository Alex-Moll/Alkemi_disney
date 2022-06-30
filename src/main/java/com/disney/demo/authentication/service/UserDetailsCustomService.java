package com.disney.demo.authentication.service;

import com.disney.demo.authentication.dto.UserDto;
import com.disney.demo.service.EmailService;
import com.disney.demo.authentication.entity.UserEntity;
import com.disney.demo.authentication.repository.UserRepository;
import com.disney.demo.exception.ParamNotFound;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsCustomService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder encoder;
    
    @Override
    public UserDetails loadUserByUsername(String username){
        
        UserEntity userEntity = userRepository.findByUsername(username);
        
        //si el user es nulo, lanza una exception sino crea un 
        //User nuevo con los get
        if(userEntity == null){
            throw new UsernameNotFoundException("Username o Password not found");
        }
        
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }
    
    public boolean save(UserDto userDto){
        
        UserEntity user = this.userRepository.findByUsername(userDto.getUsername());
        if(user != null){
            throw new UsernameNotFoundException("Username Not Found ");
        }
        
        UserEntity userEntity = new UserEntity();
        
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(encoder.encode(userDto.getPassword()));
        
        userEntity = userRepository.save(userEntity);
        
        if(userEntity != null){
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        
        return userEntity != null;
    }
    
}
