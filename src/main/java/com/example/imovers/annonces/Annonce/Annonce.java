package com.example.imovers.annonces.Annonce;

import com.example.imovers.annonces.Categorie.Categorie;
import com.example.imovers.annonces.Cite.Cite;
import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.Residence.Quartier;
import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Visibility.Visibility;
import com.example.imovers.security.AppUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity @Table @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Annonce{
    @Id
    @GeneratedValue
    private Long id;

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
    Type type ;

    @ManyToOne(cascade = CascadeType.MERGE)
//    @JsonBackReference
//    @JsonIgnore
    private Visibility visibility;

    @ManyToOne(cascade = CascadeType.MERGE)
//    @JsonBackReference
//    @JsonIgnore
    private Cite cite;

    @OneToMany(mappedBy = "annonce", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImageData> imageP;

    double price;


    private String description;
    private int nbChambres , nbSalons, nbDouches, nbCuisines, superficie;

    private int nbGarages;

    private String filenames;

    private Date datepublication;

    public Annonce(Long id ) {
        this.id = id;
    }


    public void setAllProperties(Annonce annonce){
        this.type = annonce.type != null ? annonce.type: this.type;
        this.description = annonce.description != null ? annonce.description: this.description;
        this.imageP = annonce.imageP;
        this.categorie = annonce.categorie != null ? annonce.categorie: this.categorie;
        this.visibility = annonce.visibility != null ? annonce.visibility : this.visibility;
        this.author = annonce.author != null ? annonce.author : this.author;
//        this.localisation = annonce.localisation != null ? annonce.localisation : this.localisation;
        this.nbChambres =  annonce.nbChambres ;
        this.nbCuisines =  annonce.nbCuisines ;
        this.nbSalons = annonce.nbSalons;
        this.nbDouches = annonce.nbDouches ;
        this.price = annonce.price;
        this.superficie = annonce.superficie;
	    this.nbGarages = annonce.nbGarages;
        this.filenames =annonce.filenames;
    }

}

