package com.example.imovers.annonces.Residence;

import java.util.List;
import java.util.Optional;

public interface ArrondissementService {
    Arrondissement findByName(String arrondissement);
    Arrondissement findById(long id);
    Arrondissement createArrondissement(Arrondissement arrondissement);
    Arrondissement editArrondissement(Arrondissement arrondissement);
    Arrondissement getArrondissement(Long id);
    List<Arrondissement> getArrondissements();
    List<Arrondissement> getArrondissementsByVille(Long id);
}