package com.example.imovers.api;

import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.ImageData.StorageService;
import com.example.imovers.annonces.Residence.Arrondissement;
import com.example.imovers.annonces.Residence.ArrondissementService;
import com.example.imovers.annonces.Residence.Ville;
import com.example.imovers.annonces.Residence.VilleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ArrondissementController {

    private final ArrondissementService arrondissementService;
    private final VilleService villeService;
    private final StorageService storageService;

    @PostMapping(value = "/arrondissements" )
    public ResponseEntity<Arrondissement> createArrondissement(@RequestParam MultipartFile file  ,
                                                               @RequestParam String name,
                                                               @RequestParam String ville) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/arrondissements/save").toUriString());
        Ville theVille = villeService.findByName(ville);
        Arrondissement newArrondissement  =  new Arrondissement();
        newArrondissement.setName(name);
        newArrondissement.setVille(theVille);
        Arrondissement returnArrondissement =  arrondissementService.createArrondissement(newArrondissement);
        ImageData uploadImage = storageService.uploadImageArrondissement(file, returnArrondissement);
        List<ImageData> imageDataList = new ArrayList<>();
        imageDataList.add(uploadImage);
        returnArrondissement.setFilenames(uploadImage.getName());
        returnArrondissement.setImagesArrondissment(imageDataList);
        arrondissementService.editArrondissement(returnArrondissement);
        return ResponseEntity.created(uri).body(returnArrondissement);
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
