package com.example.imovers.annonces.Residence;

import java.util.List;
import java.util.Optional;

public interface VilleService {
    Ville findByName(String ville);
    Ville findById(long id);
    Ville createVille(Ville ville);
    Ville editVille(Ville ville);
    Ville getVille(Long id);
    List<Ville> getVilles();
}