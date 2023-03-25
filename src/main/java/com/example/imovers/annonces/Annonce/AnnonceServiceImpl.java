package com.example.imovers.annonces.Annonce;

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
        log.info("Saving annonce: {}"  + annonce.type);
        return annonceRepo.saveAndFlush(annonce);
    }

    @Override
    public Annonce editAnnonce(Annonce annonce) {
        annonceRepo.saveAndFlush(annonce);
        log.info("Editing Complete");
        return annonce;
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