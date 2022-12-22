package com.example.imovers.annonces.Residence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartierRepo extends JpaRepository<Quartier, Long>{
    Quartier findQuartierByName(String name);
}
