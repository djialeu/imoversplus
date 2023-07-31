package com.example.imovers.annonces.Cite;

import java.util.List;

public interface CiteService {
    Cite createCite(Cite cite);
    Cite editCite(Cite cite);
    Cite getCite(Long id);
    List<Cite> getCites();
    Cite findByName(String cite);
    Cite findById(long id);
    List<Cite> findByQuartierId(long id);
}