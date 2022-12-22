package com.example.imovers.annonces.Categorie;

import com.example.imovers.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
@Slf4j
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepo categorieRepo;

    @Override
    public Categorie createCategorie(Categorie categorie) {
        log.info("Saving Categorie: {}" +categorie.getName() );
        return categorieRepo.saveAndFlush(categorie);
    }

    @Override
    public Categorie editCategorie(Categorie categorie) {
        return null;
    }

    @Override
    public Categorie getCategorie(Long id) {
        return categorieRepo.findById(id).orElseThrow(() -> new IllegalStateException("Categorie No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Categorie> getCategories() {
        return categorieRepo.findAll();
    }

    @Override
    public Categorie findByName(String name) {
        return categorieRepo.findCategorieByName(name);
    }
}