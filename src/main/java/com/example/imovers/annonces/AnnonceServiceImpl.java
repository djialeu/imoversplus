package com.example.imovers.annonces;

import com.example.imovers.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
@Slf4j
public class AnnonceServiceImpl implements AnnonceService {
    private final AnnonceRepo annonceRepo;
    private final SecurityService securityService;

    @Override
    public Annonce createAnnonce(Annonce annonce) {
        log.info("Saving annonce: {}" +annonce.getCategorie() + " " + annonce.type);
        return annonceRepo.saveAndFlush(annonce);
    }

    @Override
    public Annonce editAnnonce(Annonce annonce) {
        Annonce annonce1 = annonceRepo.findById(annonce.getId()).orElseThrow(() -> new IllegalStateException("No Annonce Found... Please pass a correct Annonce ID"));
        annonce1.setAllProperties(annonce);
        annonceRepo.saveAndFlush(annonce1);
        log.info("Editing Complete");
        return annonce1;
    }

    @Override
    public Annonce getAnnonce(Long id) {
        return annonceRepo.findById(id).orElseThrow(() -> new IllegalStateException("Annonce No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Annonce> getAnnonces() {
        return annonceRepo.findAll();
    }
}