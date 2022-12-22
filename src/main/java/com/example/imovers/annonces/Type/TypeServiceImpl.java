package com.example.imovers.annonces.Type;

import com.example.imovers.annonces.Type.Type;
import com.example.imovers.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
@Slf4j
public class TypeServiceImpl implements TypeService {
    private final TypeRepo typeRepo;

    @Override
    public Type createType(Type type) {
        log.info("Saving Type: {}" +type.getName() );
        return typeRepo.saveAndFlush(type);
    }

    @Override
    public Type editType(Type Type) {
        return null;
    }

    @Override
    public Type getType(Long id) {
        return typeRepo.findById(id).orElseThrow(() -> new IllegalStateException("Type No " + id + " not found   PLEASE pass a correct ID"));
    }

    @Override
    public List<Type> getTypes() {
        return typeRepo.findAll();
    }

    @Override
    public Type findByName(String type) {
        return typeRepo.findTypeByName(type);
    }
}