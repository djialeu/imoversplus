package com.example.imovers.annonces.Categorie;

import com.example.imovers.annonces.Categorie.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepo extends JpaRepository<Categorie, Long>{
    Categorie findCategorieByName(String name);
}