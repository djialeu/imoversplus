package com.example.imovers.annonces.Residence;

import java.util.List;

public interface ArrondissementService {
    Arrondissement findByName(String arrondissement);
    Arrondissement createArrondissement(Arrondissement arrondissement);
    Arrondissement editArrondissement(Arrondissement arrondissement);
    Arrondissement getArrondissement(Long id);
    List<Arrondissement> getArrondissements();
}