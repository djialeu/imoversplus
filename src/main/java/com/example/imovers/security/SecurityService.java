package com.example.imovers.security;

import java.util.List;

public interface SecurityService{
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username, String rolename);
    AppUser getUser(String username);
    List<AppUser> getUsers();
    boolean ifExist(String identifier);
}