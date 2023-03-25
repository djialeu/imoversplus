package com.example.imovers.annonces.Residence;

import com.example.imovers.annonces.ImageData.ImageData;
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
public class Ville {

    @Id
    @GeneratedValue
    private long Id;
    @NotNull
    @Column(unique = true)
    private String name;

    private String pays;

    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
    @JsonIgnore
//    @JsonManagedReference
    private List<Arrondissement> arrondissements;


    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImageData> imagesVille;

    private String filenames;

    public Ville(String name){
        this.name = name;
    }

}
