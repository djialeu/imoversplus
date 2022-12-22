package com.example.imovers.annonces.Visibility;

import com.example.imovers.annonces.Categorie.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisibilityRepo extends JpaRepository<Visibility, Long>{
    Visibility findVisibilityByName(String name);
}
