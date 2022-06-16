package com.disney.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table( name = "personajes")
@SQLDelete(sql = "UPDATE Personaje SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Personaje implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String imagen;
    
    private String nombre;
    
    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd") // formato y patron de la fecha
    private LocalDate fechaNac;
    
    private double peso;
    
    private String historia;
    
    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<Pelicula> peliculas = new ArrayList<>();
    
    //esto sig. que el campo pelicula_id de la tabla hace referencia al id de pelicula
    // y que no puede ser nulo(false) 
    @Column(name = "pelicula_id", nullable = false)
    private String peliculaId;
    
    private boolean deleted = Boolean.FALSE; 
        
}
