package com.disney.demo.entity;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table( name = "movies")
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class MovieEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    private String image;
    
    private String title;
    
    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd") // formato y patron de la fecha
    private LocalDate creationDate;
     
    @Range(min=1, max=5)
    private Integer calification;
    
    // en el ManyToOne el fetch es por defecto EAGER
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "gender_Id", referencedColumnName="id")
    @NotNull
    private GenderEntity gender;
    
    // en el ManyToMany el fetch es por defecto LAZY
    @ManyToMany(cascade = CascadeType.MERGE , 
                          fetch = FetchType.LAZY)
    @JoinTable(name = "movie_character", 
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn( name = "character_id")) 
    private List<CharacterEntity> characters = new ArrayList<>();
              
    private boolean deleted = Boolean.FALSE;

    public MovieEntity() {
        
    }
    

}
