package com.example.imovers.api;

import com.example.imovers.annonces.Categorie.Categorie;
import com.example.imovers.annonces.Categorie.CategorieService;
import com.example.imovers.annonces.Cite.Cite;
import com.example.imovers.annonces.Cite.CiteService;
import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.ImageData.StorageService;
import com.example.imovers.annonces.Residence.Arrondissement;
import com.example.imovers.annonces.Residence.Quartier;
import com.example.imovers.annonces.Residence.QuartierService;
import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Type.TypeService;
import com.example.imovers.annonces.Visibility.Visibility;
import com.example.imovers.annonces.Visibility.VisibilityService;
import com.example.imovers.annonces.Annonce.Annonce;
import com.example.imovers.annonces.Annonce.AnnonceService;
import com.example.imovers.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
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
    private final CiteService citeService;

    @PostMapping(value = "/annonces", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} , produces = "application/json")
    @ResponseBody
    public ResponseEntity<Annonce> createAnnonce(@RequestParam List<MultipartFile> files,
//                                                 @RequestParam String categorieName,
                                                 @RequestParam long type_id,
//                                                 @RequestParam String quartierName,
//                                                 @RequestParam String visibilityName,
                                                 @RequestParam double price,
//                                                 @RequestParam int nbCite,
//                                                 @RequestParam int nbPiece,
                                                 @RequestParam String description,
                                                 @RequestParam long citeId ,
//                                                 @RequestParam String localisation,
                                                 @RequestParam int nbChambre,
                                                 @RequestParam int nbSalon,
                                                 @RequestParam int nbDouche,
                                                 @RequestParam int nbCuisine,
                                                 @RequestParam int superficie,
                                                 @RequestParam int nbGarage
                                                 ) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/annonces/save").toUriString());

        Cite cite = citeService.findById(citeId);

        if ( cite != null){
            Annonce annonce = new Annonce();
            annonce.setPrice(price);
            annonce.setDescription(description);
//        cite.setLocalisation(localisation);
//        annonce.setLocalisation(localisation);
            annonce.setNbChambres(nbChambre);
//        annonce.setNbCite(nbCite);
            annonce.setSuperficie(superficie);
            annonce.setNbCuisines(nbCuisine);
            annonce.setNbDouches(nbDouche);
            annonce.setNbGarages(nbGarage);
//        cite.setName(nomCite);
//        cite.setNbPiece(nbPiece);
//        annonce.setNomCite(nomCite);
//        annonce.setNbPiece(nbPiece);
            annonce.setNbSalons(nbSalon);
//        Categorie theCategorie = categorieService.findByName(categorieName);
            Visibility theVisibility = visibilityService.findByName("APPROVED");
            Type theType = typeService.getType(type_id);
//        Quartier theQuartier = quartierService.findByName(quartierName);
//        annonce.setCategorie(theCategorie);
            annonce.setVisibility(theVisibility);
            annonce.setType(theType);
            annonce.setCite(cite);
//        cite.setQuartier(theQuartier);
            annonce.setFilenames("");
            annonce.setDatepublication(new Date());
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/annonce/addImages")
    public ResponseEntity<Annonce> addImages(@RequestParam long id , @RequestParam List<MultipartFile> files) throws IOException {
        Annonce annonce = annonceService.getAnnonce(id);
        if(annonce != null){
            List<ImageData> imageDataList = new ArrayList<>();
            files.forEach((file) -> {
                try {
                    ImageData uploadImage = storageService.uploadImage(file, annonce);
                    if(uploadImage != null){
                        imageDataList.add(uploadImage);
                        if(annonce.getFilenames().isEmpty()){
                            annonce.setFilenames(uploadImage.getName());
                        }else {
                            annonce.setFilenames(annonce.getFilenames().concat(";").concat(uploadImage.getName()));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            annonce.setImageP(imageDataList);
            Annonce rA = annonceService.editAnnonce(annonce);
            return  ResponseEntity.ok().body(rA);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/annonces/edit/visibility/{id}")
    public Annonce editAnnonceVisibility(@PathVariable long id , @RequestParam String visibility){
        Annonce annonce = annonceService.getAnnonce(id);
        annonce.setVisibility(visibilityService.findByName(visibility));
        annonceService.editAnnonce(annonce);
        return annonce;
    }

    @PostMapping("/annonces/delete/{id}")
    public ResponseEntity<String> deleteAnnonce(@PathVariable long id){
        Annonce annonce = annonceService.getAnnonce(id);
        annonce.setVisibility(visibilityService.findByName("DELETE"));
        annonceService.editAnnonce(annonce);
        return ResponseEntity.ok("Annonce edited as delete.");
    }

    @GetMapping("/annonces/{id}")
    public Annonce getAnnonce(@PathVariable long annonceId){
        return annonceService.getAnnonce(annonceId);
    }

    @GetMapping("/annonces")
    public List<Annonce> getAnnonces(){
        return annonceService.getAnnonces();
    }

    @GetMapping("/annonces/approved")
    public List<Annonce> getApprovedAnnonces(){
        return annonceService.getApprovedAnnonces();
    }

    @GetMapping("/annonces/cite/{cid}")
    public List<Annonce> getAnnoncesByCiteId(@PathVariable("cid") long cid){
        return annonceService.getAnnoncesByCiteId(cid);
    }


}
