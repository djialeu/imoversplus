package com.example.imovers.annonces.Key;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepo extends JpaRepository<Key, Long> {
    Key findKeyByKey(String key);
    void deleteKeyByKey(String key);
}
