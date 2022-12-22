package com.example.imovers.annonces.Residence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional
@Slf4j
public class ArrondissementServiceImpl implements ArrondissementService {
    private final ArrondissementRepo arrondissementRepo;

    @Override
    public Arrondissement findByName(String arrondissement) {
        return arrondissementRepo.findArrondissementByName(arrondissement);
    }

    @Override
    public Arrondissement createArrondissement(Arrondissement arrondissement) {
        return arrondissementRepo.saveAndFlush(arrondissement);
    }

    @Override
    public Arrondissement editArrondissement(Arrondissement arrondissement) {
        return null;
    }

    @Override
    public Arrondissement getArrondissement(Long id) {
        return arrondissementRepo.findById(id).orElseThrow(() -> new IllegalStateException("Arrondissement No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Arrondissement> getArrondissements() {
        return arrondissementRepo.findAll();
    }

}