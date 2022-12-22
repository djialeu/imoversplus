package com.example.imovers.api;

import com.example.imovers.annonces.*;
import com.example.imovers.annonces.Categorie.Categorie;
import com.example.imovers.annonces.Categorie.CategorieService;
import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.ImageData.StorageService;
import com.example.imovers.annonces.Residence.Arrondissement;
import com.example.imovers.annonces.Residence.ArrondissementService;
import com.example.imovers.annonces.Residence.Quartier;
import com.example.imovers.annonces.Residence.QuartierService;
import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Type.TypeService;
import com.example.imovers.annonces.Visibility.Visibility;
import com.example.imovers.annonces.Visibility.VisibilityService;
import com.example.imovers.security.AppUser;
import com.example.imovers.security.SecurityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AnnonceController{


    private final AnnonceService annonceService;
    private final SecurityService resource;
    private final CategorieService categorieService;
    private final VisibilityService visibilityService;
    private final TypeService typeService;
    private final QuartierService quartierService;
    private final StorageService storageService;

    @PostMapping(value = "/annonces", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} , produces = "application/json")
    @ResponseBody
    public ResponseEntity<Annonce> createAnnonce(@RequestParam List<MultipartFile> files,
                                                 @RequestParam String categorieName,
                                                 @RequestParam String typeName,
                                                 @RequestParam String quartierName,
                                                 @RequestParam String visibilityName,
                                                 @RequestParam double price,
                                                 @RequestParam int nbCite,
                                                 @RequestParam int nbPiece,
                                                 @RequestParam String description,
                                                 @RequestParam String nomCite ,
                                                 @RequestParam String localisation,
                                                 @RequestParam int nbChambre,
                                                 @RequestParam int nbSalon,
                                                 @RequestParam int nbDouche,
                                                 @RequestParam int nbCuisine,
                                                 @RequestParam int superficie,
                                                 @RequestParam int nbGarage
                                                 ) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/annonces/save").toUriString());
        Annonce annonce = new Annonce();
        annonce.setPrice(price);
        annonce.setDescription(description);
        annonce.setLocalisation(localisation);
        annonce.setNbChambres(nbChambre);
        annonce.setNbCite(nbCite);
        annonce.setSuperficie(superficie);
        annonce.setNbCuisines(nbCuisine);
        annonce.setNbDouches(nbDouche);
        annonce.setNbGarages(nbGarage);
        annonce.setNomCite(nomCite);
        annonce.setNbPiece(nbPiece);
        annonce.setNbSalons(nbSalon);
        Categorie theCategorie = categorieService.findByName(categorieName);
        Visibility theVisibility = visibilityService.findByName(visibilityName);
        Type theType = typeService.findByName(typeName);
        Quartier theQuartier = quartierService.findByName(quartierName);
        annonce.setCategorie(theCategorie);
        annonce.setVisibility(theVisibility);
        annonce.setType(theType);
        annonce.setQuartier(theQuartier);
        annonce.setFilenames("");
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            String principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            annonce.setAuthor(resource.getUser(principal));
            annonce.setDatepublication(new Date());
        }
        Annonce returnAnnonce = annonceService.createAnnonce(annonce);
        if(returnAnnonce != null){
            List<ImageData> imageDataList = new ArrayList<>();
            files.forEach((file) -> {
                try {
                    ImageData uploadImage = storageService.uploadImage(file, returnAnnonce);
                    if(uploadImage != null){
                        imageDataList.add(uploadImage);
                        if(returnAnnonce.getFilenames().isEmpty()){
                            returnAnnonce.setFilenames(uploadImage.getName());
                        }else {
                            returnAnnonce.setFilenames(returnAnnonce.getFilenames().concat(";").concat(uploadImage.getName()));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            returnAnnonce.setImageP(imageDataList);
            annonceService.editAnnonce(returnAnnonce);
        }
        return ResponseEntity.created(uri).body(returnAnnonce);
    }


//    public boolean upload(MultipartFile file) {
//        String principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        AppUser user = resource.getUser(principal);
//        Path root = Paths.get("src/main/resources/images/");
//        try {
//            if(!Files.exists(root)){
//                Files.createDirectories(root);
//            }
//            Files.copy(file.getInputStream(), root.resolve( user.getId() + "-" +  file.getOriginalFilename()));
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    @GetMapping("/annonces/{id}")
    public Annonce getAnnonce(@PathVariable Long annonceId){
        return annonceService.getAnnonce(annonceId);
    }

    @GetMapping("/annonces")
    public List<Annonce> getAnnonces(){
        return annonceService.getAnnonces();
    }


}