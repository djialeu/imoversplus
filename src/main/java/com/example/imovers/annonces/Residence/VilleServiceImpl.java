package com.example.imovers.annonces.Residence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor @Transactional
@Slf4j
public class VilleServiceImpl implements VilleService {
    private final VilleRepo villeRepo;

    @Override
    public Ville findByName(String ville) {
        return villeRepo.findVilleByName(ville);
    }

    @Override
    public Ville findById(long id) {
        return villeRepo.findById(id).orElseThrow(() -> new IllegalStateException("Not Found"));
    }

    @Override
    public Ville createVille(Ville ville) {
        return villeRepo.saveAndFlush(ville);
    }

    @Override
    public Ville editVille(Ville ville) {
        villeRepo.saveAndFlush(ville);
        log.info("Editing Complete");
        return ville;
    }

    @Override
    public Ville getVille(Long id) {
        return villeRepo.findById(id).orElseThrow(() -> new IllegalStateException("Ville No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Ville> getVilles() {
        return villeRepo.findAll();
    }

}