package com.example.imovers.annonces.Compte;

import com.example.imovers.annonces.Annonce.Annonce;
import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.Residence.Quartier;
import com.example.imovers.security.AppUser;
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
public class Compte {
    @Id
    @GeneratedValue
    private long Id;
    @NotNull
    @Column(unique = true)
    private String label;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AppUser> users;

    public Compte(String label){
        this.label = label;
    }
}

