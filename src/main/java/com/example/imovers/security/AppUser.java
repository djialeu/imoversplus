package com.example.imovers.security;

import com.example.imovers.annonces.Annonce.Annonce;
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
    private String name, email ,username, password;
    private String compte;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Annonce> annonces;
}
