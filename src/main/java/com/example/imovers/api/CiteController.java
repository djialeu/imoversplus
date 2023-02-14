package com.example.imovers.api;

import com.example.imovers.annonces.Cite.Cite;
import com.example.imovers.annonces.Cite.CiteService;
import com.example.imovers.annonces.ImageData.ImageData;
import com.example.imovers.annonces.ImageData.StorageService;
import com.example.imovers.annonces.Residence.Quartier;
import com.example.imovers.annonces.Residence.QuartierService;
import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private StorageService storageService;

    // Save operation
    @PostMapping("/cites")
    public Cite saveCite(
            @RequestParam MultipartFile file,
            @RequestParam String name ,
            @RequestParam String localisation ,
            @RequestParam int nbPiece ,
            @RequestParam String quartier
            ) throws IOException {
        Quartier theQuartier =  quartierService.findByName(quartier);
        Cite newCite =  new Cite();
        newCite.setNbPiece(nbPiece);
        newCite.setLocalisation(localisation);
        newCite.setName(name);
        newCite.setQuartier(theQuartier);
        Cite returnCite = service.createCite(newCite);
        ImageData uploadImage = storageService.uploadImageCite(file , newCite);
        List<ImageData> imageDataList = new ArrayList<>();
        imageDataList.add(uploadImage);
        returnCite.setFilenames(uploadImage.getName());
        returnCite.setImagesCite(imageDataList);
        service.editCite(returnCite);
        return service.createCite(returnCite);
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
