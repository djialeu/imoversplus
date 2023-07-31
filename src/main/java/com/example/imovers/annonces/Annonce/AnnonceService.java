package com.example.imovers.annonces.Annonce;


import java.util.List;

public interface AnnonceService{
    Annonce createAnnonce(Annonce annonce);
    Annonce editAnnonce(Annonce annonce);
    Annonce getAnnonce(Long id);
    List<Annonce> getAnnonces();
    List<Annonce> getAnnoncesByCiteId(long id);
}