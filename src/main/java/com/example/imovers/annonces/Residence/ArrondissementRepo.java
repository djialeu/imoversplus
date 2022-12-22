package com.example.imovers.annonces.Residence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrondissementRepo extends JpaRepository<Arrondissement, Long>{
    Arrondissement findArrondissementByName(String name);
}
