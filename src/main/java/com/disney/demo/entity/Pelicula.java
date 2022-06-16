package com.disney.demo.entity;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table( name = "peliculas")
@SQLDelete(sql = "UPDATE Pelicula SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Pelicula implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String imagen;
    
    private String titulo;
    
    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd") // formato y patron de la fecha
    private LocalDate fechaCreacion;
     
    private Integer calificacion;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    // en el ManyToOne el fetch es por defecto EAGER
    @JoinColumn(name = "genero_Id")
    @NotNull
    private Genero genero;
    
    @ManyToMany(cascade = { CascadeType.PERSIST,
                            CascadeType.MERGE }, 
                            fetch = FetchType.LAZY)
    // en el ManyToMany el fetch es por defecto LAZY
    @JoinTable(name = "pelicula_personaje", 
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn( name = "personaje_id") )
    private Set<Personaje> personajes = new HashSet<>();
              
    private boolean deleted = Boolean.FALSE;

    public Pelicula() {
    }
    

}
