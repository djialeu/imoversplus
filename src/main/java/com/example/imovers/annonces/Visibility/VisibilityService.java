package com.example.imovers.annonces.Visibility;

import com.example.imovers.annonces.Categorie.Categorie;

import java.util.List;

public interface VisibilityService {
    Visibility findByName(String visibility);
    Visibility createVisibility(Visibility Visibility);
    Visibility editVisibility(Visibility Visibility);
    Visibility getVisibility(Long id);
    List<Visibility> getVisibilities();
}