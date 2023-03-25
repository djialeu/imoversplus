package com.example.imovers.annonces.Residence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor @Transactional
@Slf4j
public class ArrondissementServiceImpl implements ArrondissementService {
    private final ArrondissementRepo arrondissementRepo;
    private final VilleRepo villeRepo;

    @Override
    public Arrondissement findByName(String arrondissement) {
        return arrondissementRepo.findArrondissementByName(arrondissement);
    }

    @Override
    public Arrondissement findById(long id) {
        return arrondissementRepo.findById(id).orElseThrow(() -> new IllegalStateException("Not Found"));
    }

    @Override
    public Arrondissement createArrondissement(Arrondissement arrondissement) {
        return arrondissementRepo.saveAndFlush(arrondissement);
    }

    @Override
    public Arrondissement editArrondissement(Arrondissement arrondissement){
        arrondissementRepo.saveAndFlush(arrondissement);
        log.info("Editing Complete");
        return arrondissement;
    }

    @Override
    public Arrondissement getArrondissement(Long id) {
        return arrondissementRepo.findById(id).orElseThrow(() -> new IllegalStateException("Arrondissement No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Arrondissement> getArrondissements() {
        return arrondissementRepo.findAll();
    }

    @Override
    public List<Arrondissement> getArrondissementsByVille(Long id) {
        Optional<Ville> ville = villeRepo.findById(id);
        if (ville.isPresent()) {
             Ville newVille = ville.orElseThrow(() -> new IllegalStateException("Ville Not found"));
            return arrondissementRepo.findArrondissementsByVille(newVille).orElseThrow(() -> new IllegalStateException("No arrondissements Found"));
        }else{
            return new ArrayList<>();
        }

    }

}