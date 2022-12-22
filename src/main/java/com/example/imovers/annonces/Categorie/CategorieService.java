package com.example.imovers.annonces.Categorie;

import java.util.List;

public interface CategorieService {
    Categorie createCategorie(Categorie Categorie);
    Categorie editCategorie(Categorie Categorie);
    Categorie getCategorie(Long id);
    List<Categorie> getCategories();
    Categorie findByName(String name);
}