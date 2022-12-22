package com.example.imovers.annonces.Visibility;

import com.example.imovers.annonces.Annonce;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Visibility{
//    REVIEW("REVIEW"),
//    APPROVED("APPROVED"),
//    REJECTED("REJECTED");

    @Id
    @GeneratedValue
    private long Id;
    @NotNull
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "visibility",cascade = CascadeType.ALL)
    @JsonIgnore
//    @JsonManagedReference
    private List<Annonce> annonces;

    public Visibility(String name){
        this.name = name;
    }

}
