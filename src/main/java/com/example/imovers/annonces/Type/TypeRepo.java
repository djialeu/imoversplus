package com.example.imovers.annonces.Type;

import com.example.imovers.annonces.Categorie.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepo extends JpaRepository<Type, Long>{
    Type findTypeByName(String type);
}
