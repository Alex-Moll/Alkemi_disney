package com.disney.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table( name = "peliculas")
public class Pelicula implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String imagen;
    
    private String titulo;
    
    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/mm/dd") // formato y patron de la fecha
    private LocalDate fechaCreacion;
    
    private Integer calificacion;
    
    @ManyToMany(cascade = { CascadeType.PERSIST, 
                            CascadeType.MERGE })
    @JoinTable(name = "pelicula_personaje", 
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn( name = "personaje_id") )
    private Set<Personaje> personajes = new HashSet<>();
    
          
}
