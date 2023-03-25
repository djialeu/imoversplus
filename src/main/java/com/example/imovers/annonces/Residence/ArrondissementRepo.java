package com.example.imovers.annonces.Residence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArrondissementRepo extends JpaRepository<Arrondissement, Long>{
    Arrondissement findArrondissementByName(String name);
    Optional<List<Arrondissement>> findArrondissementsByVille(Ville ville);
}
