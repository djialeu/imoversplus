package com.example.imovers.annonces.Compte;

import java.util.List;

public interface CompteService {
    Compte createCompte(Compte compte);
    Compte editCompte(Compte compte);
    Compte getCompte(Long id);
    List<Compte> getComptes();
    Compte findByLabel(String label);
    Compte findById(long id);
}