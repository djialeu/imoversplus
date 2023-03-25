package com.example.imovers.annonces.Residence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuartierRepo extends JpaRepository<Quartier, Long>{
    Quartier findQuartierByName(String name);
    Optional<List<Quartier>> findQuartiersByArrondissement(Arrondissement arrondissement);
}
