package com.example.imovers.api;

import com.example.imovers.annonces.Annonce;
import com.example.imovers.annonces.AnnonceService;
import com.example.imovers.annonces.Categorie.Categorie;
import com.example.imovers.annonces.Categorie.CategorieService;
import com.example.imovers.annonces.Residence.Ville;
import com.example.imovers.annonces.Residence.VilleService;
import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Type.TypeService;
import com.example.imovers.annonces.Visibility.Visibility;
import com.example.imovers.annonces.Visibility.VisibilityService;
import com.example.imovers.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class VilleController {

    private final VilleService villeService;

    @PostMapping(value = "/villes" )
    public ResponseEntity<Ville> createVille(@RequestBody Ville ville  ){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/villes/save").toUriString());
        return ResponseEntity.created(uri).body(villeService.createVille(ville));
    }

    @GetMapping("/villes/{id}")
    public Ville getVille(@PathVariable Long villeId){
        return villeService.getVille(villeId);
    }

    @GetMapping("/villes")
    public List<Ville> getVilles(){
        return villeService.getVilles();
    }


}
