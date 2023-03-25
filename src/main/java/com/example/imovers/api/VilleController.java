package com.example.imovers.api;

import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.ImageData.StorageService;
import com.example.imovers.annonces.Residence.Arrondissement;
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
public class VilleController {

    private final VilleService villeService;
    private final StorageService storageService;

    @PostMapping(value = "/villes" )
    public ResponseEntity<Ville> createVille(@RequestParam MultipartFile file,
                                             @RequestParam String name  ,
                                             @RequestParam String pays
                                             ) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/villes/save").toUriString());
        Ville newVille  = new Ville();
        newVille.setName(name);
        newVille.setPays(pays);
        Ville returnVille =  villeService.createVille(newVille);
        ImageData uploadImage = storageService.uploadImageVille(file , returnVille);
        List<ImageData> imageDataList = new ArrayList<>();
        imageDataList.add(uploadImage);
        returnVille.setFilenames(uploadImage.getName());
        returnVille.setImagesVille(imageDataList);
        villeService.editVille(returnVille);
        return ResponseEntity.created(uri).body(returnVille);
    }

    @PostMapping("/ville/addImage")
    public ResponseEntity<Ville> addImage(@RequestParam long id , @RequestParam MultipartFile file) throws IOException {
        Ville ville = villeService.findById(id);
        if(ville != null){
            ImageData uploadImage = storageService.uploadImageVille(file ,ville);
            List<ImageData> imageDataList = new ArrayList<>();
            imageDataList.add(uploadImage);
            ville.setFilenames(uploadImage.getName());
            ville.setImagesVille(imageDataList);
            Ville rville = villeService.editVille(ville);
            return  ResponseEntity.ok().body(rville);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
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
