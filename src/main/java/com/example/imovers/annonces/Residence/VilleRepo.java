package com.example.imovers.annonces.Residence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepo extends JpaRepository<Ville, Long>{
    Ville findVilleByName(String name);
}
