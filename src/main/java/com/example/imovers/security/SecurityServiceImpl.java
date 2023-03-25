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

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class SecurityServiceImpl implements SecurityService , UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findAppUserByUsername(username);
        boolean active  = user.isActive();
        if (user == null || active == false){
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
    public AppUser editUser(AppUser user) {
        userRepo.saveAndFlush(user);
        log.info("Editing Complete");
        return user;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        log.info("Saving new role {} to the database " , role.getName());
        return roleRepo.saveAndFlush(role);
    }

    @Override
    public AppRole getRole(String rolename) {
        log.info("Fecthing role {}" , rolename);
        return roleRepo.findAppRoleByName(rolename);
    }

    @Override
    public void addRoleToUser(boolean clearFirst , long userid, String rolename) {

        Optional<AppUser> user = userRepo.findById(userid);
        if(user.isPresent()){
            if(roleRepo.findAppRoleByName(rolename) != null){
                AppRole role = roleRepo.findAppRoleByName(rolename);
                if ( clearFirst ){
                    user.get().getRoles().clear();
                    user.get().getRoles().add(role);
                }else{
                    user.get().getRoles().add(role);
                }
            }
        }
        log.info("Adding role {} to user {}" , rolename , user.isPresent() ? user.get().getName() : "");
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fecthing user {}" , username);
        return userRepo.findAppUserByUsername(username);
    }

    @Override
    public AppUser findUser(long id) {
        return userRepo.findById(id).orElseThrow(() -> new IllegalStateException("User not Found"));
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