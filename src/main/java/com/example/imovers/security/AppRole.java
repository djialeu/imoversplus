package com.example.imovers.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String name ;

    public AppRole(String name){
        this.name = name;
    }
}
