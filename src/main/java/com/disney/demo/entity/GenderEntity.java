package com.disney.demo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table( name = "genders")
@SQLDelete(sql = "UPDATE Genero SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class GenderEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    private String name;
    
    private String image;
    
//    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL)
//    private List<Movie> peliculas;
    
    private boolean deleted = Boolean.FALSE;
    
}
