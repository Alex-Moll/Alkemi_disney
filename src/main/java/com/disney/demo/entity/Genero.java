package com.disney.demo.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table( name = "generos")
@SQLDelete(sql = "UPDATE Genero SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Genero implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    
    private String imagen;
    
    @OneToMany(mappedBy = "genero", cascade = CascadeType.PERSIST)
    private List<Pelicula> peliculas;
    
    private boolean deleted = Boolean.FALSE;
    
}
