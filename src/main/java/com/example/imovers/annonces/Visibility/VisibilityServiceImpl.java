package com.example.imovers.annonces.Visibility;

import com.example.imovers.annonces.Categorie.Categorie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional
@Slf4j
public class VisibilityServiceImpl implements VisibilityService {
    private final VisibilityRepo visibilityRepo;

    @Override
    public Visibility findByName(String visibility) {
        return visibilityRepo.findVisibilityByName(visibility);
    }

    @Override
    public Visibility createVisibility(Visibility visibility) {
        log.info("Saving Visibility: {}" +visibility.getName() );
        return visibilityRepo.saveAndFlush(visibility);
    }

    @Override
    public Visibility editVisibility(Visibility visibility) {
        return null;
    }

    @Override
    public Visibility getVisibility(Long id) {
        return visibilityRepo.findById(id).orElseThrow(() -> new IllegalStateException("Visibility No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Visibility> getVisibilities() {
        return visibilityRepo.findAll();
    }
}