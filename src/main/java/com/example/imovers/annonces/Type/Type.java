package com.example.imovers.annonces.Type;

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
public class Type{
//    APPARTEMENT("APPARTEMENT") ,
//    STUDIO("STUDIO"),
//    CHAMBRE("CHAMBRE"),
//    VILLA("VILLA"),
//    DUPLEX("DUPLEX");

    @Id
    @GeneratedValue
    private long Id;
    @NotNull
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    @JsonIgnore
//    @JsonManagedReference
    private List<Annonce> annonces;

    public Type(String type){
        this.name = type;
    }

}

