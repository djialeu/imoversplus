package com.example.imovers.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.imovers.annonces.Compte.Compte;
import com.example.imovers.annonces.Compte.CompteService;
import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.ImageData.StorageService;
import com.example.imovers.annonces.Residence.Ville;
import com.example.imovers.annonces.Residence.VilleService;
import com.example.imovers.security.AppRole;
import com.example.imovers.security.AppUser;
import com.example.imovers.security.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class SecurityResource{
    private final SecurityService secure;
    private  final CompteService service;
    private final StorageService storageService;
    private final VilleService villeService;

    @GetMapping("/exists")
    public ResponseEntity<Boolean> ifexists(@RequestParam String identifier){
        return ResponseEntity.ok().body(secure.ifExist(identifier));
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok().body(secure.getUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<AppUser> getUser(@RequestParam String username){
        return ResponseEntity.ok().body(secure.getUser(username));
    }

    @PostMapping("/user/addImage")
    public ResponseEntity<AppUser> addImage(@RequestParam long userId , @RequestParam MultipartFile file) throws IOException {
        AppUser user = secure.findUser(userId);
        if(user != null){
            ImageData uploadImage = storageService.uploadImageAppUser(file , user);
            List<ImageData> imageDataList = new ArrayList<>();
            imageDataList.add(uploadImage);
            user.setFilenames(uploadImage.getName());
            user.setImagesUser(imageDataList);
            AppUser ruser = secure.editUser(user);
            return  ResponseEntity.ok().body(ruser);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/users/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user , @RequestParam() long compte){
        String defaultRole = "ROLE_ADMIN";
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/users/save").toUriString());
        Compte associateCompte = service.findById(compte);
        user.setCompte(associateCompte);
        AppUser tuser =  secure.saveUser(user);
        secure.addRoleToUser(true ,tuser.getId() , defaultRole);
        if(villeService.findByName(user.getVille()) == null){
            Ville ville = new Ville();
            ville.setPays(user.getPays());
            ville.setName(user.getVille());
            villeService.createVille(ville);
        }
        return ResponseEntity.created(uri).body(tuser);
    }

    @PostMapping("/role/save")
    public ResponseEntity<AppRole> saveRole(@RequestBody AppRole role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/role/save").toUriString());
        return ResponseEntity.created(uri).body(secure.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestParam boolean clearFirst ,@RequestBody RoleToUserForm form){
        secure.addRoleToUser(clearFirst , form.getUserid(), form.getRolename());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refreshtoken")
    public void refreshToken(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser user = secure.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(AppRole::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token" , access_token);
                tokens.put("refresh_token" , refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception e){
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else
            throw new RuntimeException("Refresh Token is missing");

    }


}

@Data
class RoleToUserForm{
    private long userid;
    private String rolename;
}
