package com.example.imovers.annonces.Cite;

import com.example.imovers.annonces.Annonce.Annonce;
import com.example.imovers.annonces.Categorie.Categorie;
import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.Residence.Quartier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.example.imovers.security.AppUser;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cite {

    @Id
    @GeneratedValue
    private long Id;
    @NotNull
    @Column(unique = true)
    private String name;

    @ManyToOne()
    //    @JsonBackReference
    //    @JsonIgnore
    private AppUser author;

    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "categorie_id")
//    @JsonBackReference
//    @JsonIgnore
    private Categorie categorie;

    @ManyToOne(cascade = CascadeType.MERGE)
//    @JsonBackReference
//    @JsonIgnore
    private Quartier quartier ;

    @OneToMany(mappedBy = "cite", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImageData> imagesCite;

    private String filenames;

    private String localisation;

    private int nbPiece;


    @OneToMany(mappedBy = "cite", cascade = CascadeType.ALL)
    @JsonIgnore
//    @JsonManagedReference
    private List<Annonce> annonces;

    public Cite(String cite){
        this.name = cite;
    }

    public Cite(String nomCite, Quartier theQuartier, String localisation, int nbPiece) {
        this.name = nomCite;
        this.localisation = localisation;
        this.nbPiece = nbPiece;
    }
}

