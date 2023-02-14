package com.example.imovers.api;

import com.example.imovers.annonces.Residence.Arrondissement;
import com.example.imovers.annonces.Residence.ArrondissementService;
import com.example.imovers.annonces.Residence.Quartier;
import com.example.imovers.annonces.Residence.QuartierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class QuartierController {

    private final QuartierService quartierService;
    private final ArrondissementService arrondissementService;

    @PostMapping(value = "/quartiers" )
    public ResponseEntity<Quartier> createQuartier(@RequestBody Quartier quartier  , @RequestParam String arrondissement){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/quartiers/save").toUriString());
        Arrondissement theArrondissement = arrondissementService.findByName(arrondissement);
        quartier.setArrondissement(theArrondissement);
        return ResponseEntity.created(uri).body(quartierService.createQuartier(quartier));
    }

    @GetMapping("/quartiers/{id}")
    public Quartier getQuartier(@PathVariable Long quartierId){
        return quartierService.getQuartier(quartierId);
    }

    @GetMapping("/quartiers")
    public List<Quartier> getQuartiers(){
        return quartierService.getQuartiers();
    }


}
