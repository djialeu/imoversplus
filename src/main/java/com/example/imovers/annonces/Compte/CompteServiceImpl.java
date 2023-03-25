package com.example.imovers.annonces.Compte;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
@Slf4j
public class CompteServiceImpl implements CompteService {
    private final CompteRepo compteRepo;

    @Override
    public Compte createCompte(Compte compte) {
        log.info("Saving Compte: {}" + compte.getLabel() );
        return compteRepo.saveAndFlush(compte);
    }

    @Override
    public Compte editCompte(Compte compte) {
        compteRepo.saveAndFlush(compte);
        log.info("Editing Complete");
        return compte;
    }

    @Override
    public Compte getCompte(Long id) {
        return compteRepo.findById(id).orElseThrow(() -> new IllegalStateException("Compte No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Compte> getComptes() {
        return compteRepo.findAll();
    }

    @Override
    public Compte findByLabel(String label) {
        return compteRepo.findCompteByLabel(label);
    }

    @Override
    public Compte findById(long id) {
        return compteRepo.findById(id).orElseThrow(() -> new IllegalStateException("Not Found"));
    }


}