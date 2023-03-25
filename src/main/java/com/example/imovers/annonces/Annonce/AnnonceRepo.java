package com.example.imovers.annonces.Annonce;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnnonceRepo extends JpaRepository<Annonce, Long>{
}
