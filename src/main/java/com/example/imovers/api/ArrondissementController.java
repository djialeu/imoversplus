package com.example.imovers.api;

import com.example.imovers.annonces.Cite.Cite;
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
        Arrondissement newArrondissement  =  new Arrondissement();
        if(villeService.findByName(ville) != null){
            Ville theVille = villeService.findByName(ville);
            newArrondissement.setVille(theVille);
        }
        newArrondissement.setName(name);
        Arrondissement returnArrondissement =  arrondissementService.createArrondissement(newArrondissement);
        ImageData uploadImage = storageService.uploadImageArrondissement(file, returnArrondissement);
        List<ImageData> imageDataList = new ArrayList<>();
        imageDataList.add(uploadImage);
        returnArrondissement.setFilenames(uploadImage.getName());
        returnArrondissement.setImagesArrondissment(imageDataList);
        arrondissementService.editArrondissement(returnArrondissement);
        return ResponseEntity.created(uri).body(returnArrondissement);
    }

    @PostMapping("/arrondissement/addImage")
    public ResponseEntity<Arrondissement> addImage(@RequestParam long id , @RequestParam MultipartFile file) throws IOException {
        Arrondissement arrondissement = arrondissementService.findById(id);
        if(arrondissement != null){
            ImageData uploadImage = storageService.uploadImageArrondissement(file ,arrondissement);
            List<ImageData> imageDataList = new ArrayList<>();
            imageDataList.add(uploadImage);
            arrondissement.setFilenames(uploadImage.getName());
            arrondissement.setImagesArrondissment(imageDataList);
            Arrondissement rA = arrondissementService.editArrondissement(arrondissement);
            return  ResponseEntity.ok().body(rA);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/arrondissements/{id}")
    public Arrondissement getArrondissement(@PathVariable Long arrondissementId){
        return arrondissementService.getArrondissement(arrondissementId);
    }

    @GetMapping("/arrondissements/ville")
    public List<Arrondissement> getArrondissementsByVille(@RequestParam() Long villeId){
        return arrondissementService.getArrondissementsByVille(villeId);
    }

    @GetMapping("/arrondissements")
    public List<Arrondissement> getArrondissements(){
        return arrondissementService.getArrondissements();
    }


}
