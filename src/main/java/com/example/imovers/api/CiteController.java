package com.example.imovers.api;

import com.example.imovers.annonces.Annonce.Annonce;
import com.example.imovers.annonces.Categorie.Categorie;
import com.example.imovers.annonces.Categorie.CategorieService;
import com.example.imovers.annonces.Cite.Cite;
import com.example.imovers.annonces.Cite.CiteService;
import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.ImageData.StorageService;
import com.example.imovers.security.AppUser;
import com.example.imovers.security.SecurityService;
import com.example.imovers.annonces.Residence.Quartier;
import com.example.imovers.annonces.Residence.QuartierService;
import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CiteController {

    // Annotation
    @Autowired
    private CiteService service;
    @Autowired
    private QuartierService quartierService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private SecurityService resource;

    // Save operation
    @PostMapping("/cites")
    public ResponseEntity<Cite> saveCite(
            @RequestParam MultipartFile file,
            @RequestParam String categorieName,
            @RequestParam String name ,
            @RequestParam String localisation ,
            @RequestParam int nbPiece ,
            @RequestParam long quartier_id
            ) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/cites").toUriString());

        Categorie categorie = categorieService.findByName(categorieName);
        Quartier theQuartier =  quartierService.getQuartier(quartier_id);
        Cite newCite =  new Cite();
        newCite.setNbPiece(nbPiece);
        newCite.setLocalisation(localisation);
        newCite.setName(name);
        newCite.setCategorie(categorie);
        newCite.setQuartier(theQuartier);
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            String principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            newCite.setAuthor(resource.getUser(principal));
        }
        Cite returnCite = service.createCite(newCite);
        ImageData uploadImage = storageService.uploadImageCite(file , newCite);
        List<ImageData> imageDataList = new ArrayList<>();
        imageDataList.add(uploadImage);
        returnCite.setFilenames(uploadImage.getName());
        returnCite.setImagesCite(imageDataList);
        service.editCite(returnCite);
        return  ResponseEntity.created(uri).body(returnCite);
    }

    @PostMapping("/cite/addImage")
    public ResponseEntity<Cite> addImage(@RequestParam long citeId , @RequestParam MultipartFile file) throws IOException {
        Cite cite = service.findById(citeId);
        if(cite != null){
            ImageData uploadImage = storageService.uploadImageCite(file ,cite);
            List<ImageData> imageDataList = new ArrayList<>();
            imageDataList.add(uploadImage);
            cite.setFilenames(uploadImage.getName());
            cite.setImagesCite(imageDataList);
            Cite rcite = service.editCite(cite);
            return  ResponseEntity.ok().body(rcite);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/cites/{name}")
    public Cite searchCite(@PathVariable("name") String name){
        return service.findByName(name);
    }

    // Read operation
    @GetMapping("/cites")
    public List<Cite> fetchCiteList()
    {
        return service.getCites();
    }


    // Update operation
    @PutMapping("/cites/{id}")
    public Cite updateCite(@RequestBody Cite cite,
               @PathVariable("id") Long id)
    {
        return null;
    }

    // Delete operation
    @DeleteMapping("/cites/{id}")
    public String deleteCiteById(@PathVariable("id")
                                         Long citeId)
    {
        return null;
    }
}
