package com.example.imovers.annonces.Cite;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
@Slf4j
public class CiteServiceImpl implements CiteService {
    private final CiteRepo citeRepo;

    @Override
    public Cite createCite(Cite cite) {
        log.info("Saving Cite: {}" + cite.getName() );
        return citeRepo.saveAndFlush(cite);
    }

    @Override
    public Cite editCite(Cite cite) {
        citeRepo.saveAndFlush(cite);
        log.info("Editing Complete");
        return cite;
    }

    @Override
    public Cite getCite(Long id) {
        return citeRepo.findById(id).orElseThrow(() -> new IllegalStateException("Cite No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Cite> getCites() {
        return citeRepo.findAll();
    }

    @Override
    public Cite findByName(String cite) {
        return citeRepo.findCiteByName(cite);
    }

    @Override
    public Cite findById(long id) {
        return citeRepo.findById(id).orElseThrow(() -> new IllegalStateException("Cite Not found"));
    }

    @Override
    public List<Cite> findByQuartierId(long id) {
        return citeRepo.findByQuartierId(id);
    }

    @Override
    public List<Cite> findByAppUserId(long id) {
        return citeRepo.findByAppUserId(id);
    }

    @Transactional
    @Override
    public void deleteCite(long id) {
        Cite cite =  citeRepo.findById(id).orElse(null);
        if (cite != null){
            citeRepo.delete(cite);
        }
    }

}