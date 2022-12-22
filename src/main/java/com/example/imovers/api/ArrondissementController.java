package com.example.imovers.api;

import com.example.imovers.annonces.Residence.Arrondissement;
import com.example.imovers.annonces.Residence.ArrondissementService;
import com.example.imovers.annonces.Residence.Ville;
import com.example.imovers.annonces.Residence.VilleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ArrondissementController {

    private final ArrondissementService arrondissementService;
    private final VilleService villeService;

    @PostMapping(value = "/arrondissements" )
    public ResponseEntity<Arrondissement> createArrondissement(@RequestBody Arrondissement arrondissement  , @RequestParam String ville){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/arrondissements/save").toUriString());
        Ville theVille = villeService.findByName(ville);
        arrondissement.setVille(theVille);
        return ResponseEntity.created(uri).body(arrondissementService.createArrondissement(arrondissement));
    }

    @GetMapping("/arrondissements/{id}")
    public Arrondissement getArrondissement(@PathVariable Long arrondissementId){
        return arrondissementService.getArrondissement(arrondissementId);
    }

    @GetMapping("/arrondissements")
    public List<Arrondissement> getArrondissements(){
        return arrondissementService.getArrondissements();
    }


}
