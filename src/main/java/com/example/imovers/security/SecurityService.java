package com.example.imovers.security;

import java.util.List;

public interface SecurityService{
    AppUser saveUser(AppUser user);
    AppUser editUser(AppUser user);
    AppRole saveRole(AppRole role);
    AppRole getRole(String rolename);
    void addRoleToUser(boolean clearFirst ,long userid, String rolename);
    AppUser getUser(String username);
    AppUser findUser(long id);
    List<AppUser> getUsers();
    boolean ifExist(String identifier);
}