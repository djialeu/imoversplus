package com.example.imovers.annonces.Residence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional
@Slf4j
public class QuartierServiceImpl implements QuartierService {
    private final QuartierRepo quartierRepo;

    @Override
    public Quartier findByName(String quartier) {
        return quartierRepo.findQuartierByName(quartier);
    }

    @Override
    public Quartier createQuartier(Quartier quartier) {
        return quartierRepo.saveAndFlush(quartier);
    }

    @Override
    public Quartier editQuartier(Quartier quartier) {
        return null;
    }

    @Override
    public Quartier getQuartier(Long id) {
        return quartierRepo.findById(id).orElseThrow(() -> new IllegalStateException("Quartier No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Quartier> getQuartiers() {
        return quartierRepo.findAll();
    }

}