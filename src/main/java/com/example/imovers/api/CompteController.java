package com.example.imovers.api;

import com.example.imovers.annonces.Compte.CompteService;
import com.example.imovers.annonces.Compte.Compte;
import com.example.imovers.annonces.Compte.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class CompteController {
    // Annotation
    @Autowired
    private CompteService service;

    // Save operation
    @PostMapping("/comptes")
    public Compte saveCompte(
            @RequestBody Compte Compte)
    {

        return service.createCompte(Compte);
    }

    // Read operation
    @GetMapping("/comptes")
    public List<Compte> fetchCompteList()
    {
        return service.getComptes();
    }

    // Update operation
    @PutMapping("/comptes/{id}")
    public Compte
    updateCompte(@RequestBody Compte Compte,
               @PathVariable("id") Long id)
    {

        return null;
    }

    // Delete operation
    @DeleteMapping("/comptes/{id}")
    public String deleteCompteById(@PathVariable("id")
                                         Long CompteId)
    {
        return null;
    }
}
