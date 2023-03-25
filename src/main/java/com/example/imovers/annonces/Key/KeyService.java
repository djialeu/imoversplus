package com.example.imovers.annonces.Key;

public interface KeyService {
    Key generateKey();
    void deleteKey(String key);
    Key getKey(String key);
}
