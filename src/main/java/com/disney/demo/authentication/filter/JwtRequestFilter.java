package com.disney.demo.authentication.filter;

import com.disney.demo.authentication.service.JwtUtils;
import com.disney.demo.authentication.service.UserDetailsCustomService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        final String authorizationHeader = request.getHeader("Authorization");
        
        String username = null;
        String jwt = null;
        
        //aqui nos indica si el header de postman viene nulo o no
        // y luego al "Bearer" le saca los 7 primeros caract y subtrae el username
        
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtils.extractUsername(jwt);
        }
        
        //Aqui pregunta si username != null y si el contexto de SpringSecurity == null
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            
            //crea un userDetails y le setea en el met loadUser.... la entity UserEntity
            UserDetails userDetails = this.userDetailsCustomService.loadUserByUsername(username);
            
            if(jwtUtils.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authReq = 
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
                
//                Authentication auth = (Authentication) authenticationManager.authenticate(authReq);
                
                SecurityContextHolder.getContext().setAuthentication(authReq);
            }
        }
        chain.doFilter(request, response);
    }
    
}
