package com.example.imovers.annonces.Categorie;

import com.example.imovers.annonces.Annonce.Annonce;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie{

//    SALE
//    LOCATION

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    @JsonIgnore
//    @JsonManagedReference
    private List<Annonce> annonces;

    public Categorie(String name){
        this.name = name;
    }

}