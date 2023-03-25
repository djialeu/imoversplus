package com.example.imovers.annonces.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Key {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String key;
}
