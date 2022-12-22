package com.example.imovers.annonces.Residence;

import java.util.List;

public interface VilleService {
    Ville findByName(String ville);
    Ville createVille(Ville ville);
    Ville editVille(Ville ville);
    Ville getVille(Long id);
    List<Ville> getVilles();
}