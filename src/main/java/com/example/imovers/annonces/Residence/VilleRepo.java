package com.example.imovers.annonces.Residence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VilleRepo extends JpaRepository<Ville, Long>{
    Ville findVilleByName(String name);
}
