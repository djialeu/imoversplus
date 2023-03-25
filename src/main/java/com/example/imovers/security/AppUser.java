package com.example.imovers.security;

import com.example.imovers.annonces.Annonce.Annonce;
import com.example.imovers.annonces.Cite.Cite;
import com.example.imovers.annonces.Compte.Compte;
import com.example.imovers.annonces.ImageData.ImageData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class AppUser{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email ,username;
    private String password , pays , ville;
    @Column(columnDefinition = "boolean default true")
    private boolean isActive = true;
    private String filenames;
    @ManyToOne()
    private Compte compte;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cite> cites;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImageData> imagesUser;
}
