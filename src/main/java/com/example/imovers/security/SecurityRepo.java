package com.example.imovers.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepo extends JpaRepository<AppUser, Long>{
    AppUser findAppUserByUsername(String username);
    AppUser findAppUserByEmail(String email);
}

interface RoleRepo extends JpaRepository<AppRole, Long>{
    AppRole findAppRoleByName(String name);
}

