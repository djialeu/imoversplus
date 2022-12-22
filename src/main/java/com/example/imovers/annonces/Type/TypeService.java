package com.example.imovers.annonces.Type;

import com.example.imovers.annonces.Type.Type;

import java.util.List;

public interface TypeService {
    Type createType(Type type);
    Type editType(Type type);
    Type getType(Long id);
    List<Type> getTypes();
    Type findByName(String type);
}