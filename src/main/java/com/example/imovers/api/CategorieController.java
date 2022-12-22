package com.example.imovers.api;


import com.example.imovers.annonces.Categorie.Categorie;
import com.example.imovers.annonces.Categorie.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CategorieController {

    // Annotation
    @Autowired
    private CategorieService service;

    // Save operation
    @PostMapping("/categories")
    public Categorie saveCategorie(
            @RequestBody Categorie categorie)
    {

        return service.createCategorie(categorie);
    }

    // Read operation
    @GetMapping("/categories")
    public List<Categorie> fetchCategorieList()
    {
        return service.getCategories();
    }

    // Update operation
    @PutMapping("/categories/{id}")
    public Categorie
    updateCategorie(@RequestBody Categorie categorie,
                     @PathVariable("id") Long id)
    {

        return null;
    }

    // Delete operation
    @DeleteMapping("/categories/{id}")
    public String deleteCategorieById(@PathVariable("id")
                                               Long CategorieId)
    {
        return null;
    }

}
