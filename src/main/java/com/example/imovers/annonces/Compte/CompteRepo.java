package com.example.imovers.annonces.Compte;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepo extends JpaRepository<Compte, Long>{
    Compte findCompteByLabel(String label);
}
