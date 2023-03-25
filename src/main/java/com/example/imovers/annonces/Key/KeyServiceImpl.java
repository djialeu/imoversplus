package com.example.imovers.annonces.Key;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KeyServiceImpl implements KeyService{
    private final KeyRepo keyRepo;

    @Override
    public Key generateKey() {
        Key key = new Key();
        Random random = new Random();
        int randomWithNextInt = random.nextInt();
        String k =  "_" + new Date().toInstant().toEpochMilli() * randomWithNextInt;
        key.setKey(k);
        return keyRepo.saveAndFlush(key);
    }

    @Override
    public void deleteKey(String key) {
        keyRepo.deleteKeyByKey(key);
    }

    @Override
    public Key getKey(String key) {
        return keyRepo.findKeyByKey(key);
    }
}
