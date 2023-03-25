package com.example.imovers.config;

import com.example.imovers.annonces.Categorie.Categorie;
import com.example.imovers.annonces.Categorie.CategorieService;
import com.example.imovers.annonces.Compte.Compte;
import com.example.imovers.annonces.Compte.CompteService;
import com.example.imovers.annonces.Residence.*;
import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Type.TypeService;
import com.example.imovers.annonces.Visibility.Visibility;
import com.example.imovers.annonces.Visibility.VisibilityService;
import com.example.imovers.security.AppRole;
import com.example.imovers.security.AppUser;
import com.example.imovers.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataFixture implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CompteService compteService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private VisibilityService visibilityService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private VilleService villeService;

    @Autowired
    private ArrondissementService arrondissementService;

    @Autowired
    private QuartierService quartierService;

    boolean onload = false;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //Comptes
        List<Compte> comptes = new ArrayList<>();
        List<Compte> real_comptes = new ArrayList<>();
        comptes.add(new Compte("Agence Immobilière")) ;
        comptes.add(new Compte("Utilisateur Simple"));
        comptes.add(new Compte("Location de Véhicule"));
        comptes.add(new Compte("Agent Immobilier"));
        comptes.add(new Compte("Demenagement"));
        comptes.add(new Compte("Compte Privé"));
        comptes.add(new Compte("Bailleur"));
        comptes.add(new Compte("Location d'engins"));
        comptes.forEach(compte -> {
            if(compteService.findByLabel(compte.getLabel()) == null){
                Compte tmp = compteService.createCompte(compte);
//                comptes.remove(compte);
                real_comptes.add(tmp);
            }
        } );

        //Roles
        List<AppRole> roles = new ArrayList<>();
        List<AppRole> real_roles = new ArrayList<>();
        roles.add(new AppRole("ROLE_USER"));
        roles.add(new AppRole("ROLE_ADMIN"));
        roles.add(new AppRole("ROLE_SUPER_ADMIN"));
        roles.forEach(role -> {
            if(securityService.getRole(role.getName()) == null){
                AppRole tmp = securityService.saveRole(role);
//                roles.remove(role);
                real_roles.add(tmp);
            }
        });

        //Visibilities
        List<Visibility> visibilities = new ArrayList<>();
        List<Visibility> real_visibilities = new ArrayList<>();
        visibilities.add(new Visibility("REVIEW"));
        visibilities.add(new Visibility("APPROVED"));
        visibilities.add(new Visibility("REJECTED"));
        visibilities.add(new Visibility("DELETE"));
        visibilities.add(new Visibility("UNAVAILABLE"));
        visibilities.forEach(visibility -> {
            if(visibilityService.findByName(visibility.getName()) == null){
                Visibility tmp = visibilityService.createVisibility(visibility);
//                visibilities.remove(visibility);
                real_visibilities.add(tmp);
            }
        });

        //Types
        List<Type> types = new ArrayList<>();
        List<Type> real_types = new ArrayList<>();
        types.add(new Type("APPARTEMENT"));
        types.add(new Type("STUDIO MODERNE"));
        types.add(new Type("CHAMBRE MODERNE"));
        types.add(new Type("VILLA"));
        types.add(new Type("DUPLEX"));
        types.forEach(type -> {
            if(typeService.findByName(type.getName()) == null){
                Type tmp = typeService.createType(type);
//                types.remove(type);
                real_types.add(tmp);
            }
        });

        //Villes
        List<Ville> villes = new ArrayList<>();
        List<Ville> real_villes = new ArrayList<>();
        villes.add(new Ville(0L ,  "Douala" , "Cameroun",  new ArrayList<>() , new ArrayList<>(), null));
        villes.add(new Ville(0L,"Yaoundé" , "Cameroun" , new ArrayList<>() ,new ArrayList<>(), null));
        villes.add(new Ville(0L,"Bafoussam" , "Cameroun" , new ArrayList<>() ,new ArrayList<>(), null));
        villes.add(new Ville(0L,"Lille" , "France" , new ArrayList<>() ,new ArrayList<>(), null));
        villes.add(new Ville(0L,"Paris" , "France" , new ArrayList<>() ,new ArrayList<>(), null));
        villes.forEach(ville -> {
            if(villeService.findByName(ville.getName()) == null){
                Ville tmp = villeService.createVille(ville);
//                villes.remove(ville);
                real_villes.add(tmp);
            }
        });

        //Arrondissements
        List<Arrondissement> arrondissements = new ArrayList<>();
        List<Arrondissement> real_arrondissements = new ArrayList<>();
        arrondissements.add(new Arrondissement(0L ,  "Douala I" ,   new ArrayList<>() , new ArrayList<>(), null, real_villes.get(0)));
        arrondissements.add(new Arrondissement(0L,"Yaoundé I" ,  new ArrayList<>() ,new ArrayList<>(), null, real_villes.get(1)));
        arrondissements.add(new Arrondissement(0L,"Douala II" ,  new ArrayList<>() ,new ArrayList<>(), null, real_villes.get(0)));
        arrondissements.add(new Arrondissement(0L,"Yaoundé II" , new ArrayList<>() ,new ArrayList<>(), null, real_villes.get(1)));
        arrondissements.add(new Arrondissement(0L,"Yaoundé III" , new ArrayList<>() ,new ArrayList<>(), null, real_villes.get(1)));
        arrondissements.forEach(arrondissement -> {
            if(arrondissementService.findByName(arrondissement.getName()) == null){
                Arrondissement tmp = arrondissementService.createArrondissement(arrondissement);
//                arrondissements.remove(arrondissement);
                real_arrondissements.add(tmp);
            }
        });

        //Quartiers
        List<Quartier> quartiers = new ArrayList<>();
        List<Quartier> real_quartiers = new ArrayList<>();
        quartiers.add(new Quartier(0L ,  "Bonanjo" ,   new ArrayList<>() ,  real_arrondissements.get(0)));
        quartiers.add(new Quartier(0L ,  "Nlongkak" ,   new ArrayList<>() ,  real_arrondissements.get(1)));
        quartiers.add(new Quartier(0L ,  "New Bell" ,   new ArrayList<>() ,  real_arrondissements.get(2)));
        quartiers.add(new Quartier(0L ,  "Tsinga" ,   new ArrayList<>() ,  real_arrondissements.get(3)));
        quartiers.add(new Quartier(0L ,  "Efoulan" ,   new ArrayList<>() ,  real_arrondissements.get(4)));
        quartiers.forEach(quartier -> {
            if(quartierService.findByName(quartier.getName()) == null){
                Quartier tmp = quartierService.createQuartier(quartier);
//                quartiers.remove(quartier);
                real_quartiers.add(tmp);
            }
        });

        Categorie vente = categorieService.createCategorie(new Categorie("VENTE"));
        Categorie location = categorieService.createCategorie(new Categorie("LOCATION"));


        AppUser usernew = new AppUser(46L , "ADMINISTRATEUR" , "+237690514269", "new", "123456", "Cameroun" , "Bafoussam" , true , null , real_comptes.get(5), new ArrayList<>(), new ArrayList<>() , new ArrayList<>());
        if(!securityService.ifExist(usernew.getUsername())){
            securityService.saveUser(usernew);
            securityService.addRoleToUser(true ,usernew.getId(), roles.get(2).getName());
        }

        if(!onload){
            return;
        }

    }
}
