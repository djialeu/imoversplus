package com.example.imovers.annonces.Residence;

import java.util.List;

public interface QuartierService {
    Quartier findByName(String quartier);
    Quartier createQuartier(Quartier quartier);
    Quartier editQuartier(Quartier quartier);
    Quartier getQuartier(Long id);
    List<Quartier> getQuartiers();
    List<Quartier> getQuartiersByArrondissement(Long id);
}