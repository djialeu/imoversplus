package com.example.imovers.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class SecurityServiceImpl implements SecurityService , UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findAppUserByUsername(username);
        if (user == null){
            log.info("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else {
            log.info("User {} found in database", user.getUsername());
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(s -> authorities.add(new SimpleGrantedAuthority(s.getName())));
        return new User(user.getUsername() , user.getPassword() , authorities);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} to the database " , user.getName());
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.saveAndFlush(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        log.info("Saving new role {} to the database " , role.getName());
        return roleRepo.saveAndFlush(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("Adding role {} to user {}" , rolename , username);
        AppUser user = userRepo.findAppUserByUsername(username);
        AppRole role = roleRepo.findAppRoleByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fecthing user {}" , username);
        return userRepo.findAppUserByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public boolean ifExist(String identifier) {
        boolean ifExist = true;
        if (userRepo.findAppUserByUsername(identifier) == null && userRepo.findAppUserByEmail(identifier) == null){
            ifExist = false;
        }
        return ifExist;
    }
}