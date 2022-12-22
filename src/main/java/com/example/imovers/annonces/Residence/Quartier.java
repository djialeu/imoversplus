package com.example.imovers.annonces.Residence;

import com.example.imovers.annonces.Annonce;
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
public class Quartier {

    @Id
    @GeneratedValue
    private long Id;
    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "quartier", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Annonce> annonces;

    @ManyToOne(cascade = CascadeType.MERGE)
//    @JsonBackReference
//    @JsonIgnore
    private Arrondissement arrondissement ;

    public Quartier(String quartier){
        this.name = quartier;
    }

}
