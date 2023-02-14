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
public class Arrondissement {

    @Id
    @GeneratedValue
    private long Id;
    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "arrondissement", cascade = CascadeType.ALL)
    @JsonIgnore
//    @JsonManagedReference
    private List<Quartier> quartiers;


    @OneToMany(mappedBy = "arrondissement", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImageData> imagesArrondissment;

    private String filenames;

    @ManyToOne(cascade = CascadeType.MERGE)
//    @JsonBackReference
//    @JsonIgnore
    Ville ville ;

    public Arrondissement(String name){
        this.name = name;
    }

}
