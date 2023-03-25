package com.example.imovers;

import com.example.imovers.annonces.Annonce.AnnonceService;
import com.example.imovers.annonces.Categorie.Categorie;
import com.example.imovers.annonces.Categorie.CategorieService;
import com.example.imovers.annonces.Residence.*;
import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Type.TypeService;
import com.example.imovers.annonces.Visibility.Visibility;
import com.example.imovers.annonces.Visibility.VisibilityService;
import com.example.imovers.security.AppRole;
import com.example.imovers.security.AppUser;
import com.example.imovers.security.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ImoversApplication {


	public static void main(String[] args) {
		SpringApplication.run(ImoversApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean 
	// CommandLineRunner run(SecurityService sec){
	// 	return args -> {
	// 		AppUser usernew = new AppUser(46L , "ADMINISTRATEUR" , "djialeu@gmail.com", "new", "123456", "Bailleur" , new ArrayList<>(), new ArrayList<>());
	// 		if(!sec.ifExist(usernew.getUsername())){
	// 			AppRole admin = new AppRole("ROLE_ADMIN");
	// 			sec.saveRole(admin);
	// 			sec.saveUser(usernew);
	// 			sec.addRoleToUser(usernew.getUsername(), admin.getName());
	// 		}
	// 	};
	// }


//	@Bean
//	CommandLineRunner run(SecurityService sec, CategorieService categorieService, TypeService typeService, VisibilityService visibilityService, VilleService villeService, ArrondissementService arrondissementService, QuartierService quartierService, AnnonceService annonceService) {
//		return args -> {
//
//			Quartier Bonanjo = new Quartier("Bonanjo");
//			Quartier Bessengué = new Quartier("Bessengué");
//			Quartier Bonapriso = new Quartier("Bonapriso");
//			Quartier Deido = new Quartier("Deido");
//			Quartier Mimboman = new Quartier("Mimboman");
//
//			Quartier mvog_ada = new Quartier("Mvog-Ada");
//			Quartier Soa = new Quartier("Soa");
//			Quartier Essos = new Quartier("Essos");
//			Quartier Mendong = new Quartier("Mendong");
//
//
//			//users
//			AppUser donker = new AppUser(null, "DJIALEU", "djialeu@gmail.com", "donker", "123456", "Bailleur" , new ArrayList<>(), new ArrayList<>());
//			AppUser ric = new AppUser(null, "PEWO", "pewo@gmail.com", "ric", "123456", "Utilisateur" ,new ArrayList<>(), new ArrayList<>());
//			AppUser talla = new AppUser(null, "TALLA", "pewo@gmail.com", "talla", "123456", "Agence Immobiliere" ,new ArrayList<>(), new ArrayList<>());
////			AppUser Ubayeur = new AppUser(null, "Bayeur", "bayeur@gmail.com", "bayeur", "123456", new ArrayList<>(), new ArrayList<>());
////			AppUser UAI = new AppUser(null, "Agent Imo", "agentImo@gmail.com", "agentImo", "123456", new ArrayList<>(), new ArrayList<>());
////			AppUser UAV = new AppUser(null, "AgenceVoyage", "agv@gmail.com", "agv", "123456", new ArrayList<>(), new ArrayList<>());
////			AppUser UAEI = new AppUser(null, "Agence Imo", "agi@gmail.com", "agi", "123456", new ArrayList<>(), new ArrayList<>());
////			AppUser UD = new AppUser(null, "Demenagement", "demenag@gmail.com", "demenagement", "123456", new ArrayList<>(), new ArrayList<>());
////			AppUser ULV = new AppUser(null, "Location Vehicule", "lv@gmail.com", "lv", "123456", new ArrayList<>(), new ArrayList<>());
////			AppUser ULE = new AppUser(null, "Location Engin", "le@gmail.com", "le", "123456", new ArrayList<>(), new ArrayList<>());
////			AppUser UP = new AppUser(null, "TALLA", "boss@gmail.com", "boss", "123456", new ArrayList<>(), new ArrayList<>());
//
//			//roles
////			AppRole bayeur = new AppRole("ROLE_B");
////			AppRole agentImo = new AppRole("ROLE_AI");
////			AppRole agenceImo = new AppRole("ROLE_AEI");
////			AppRole agenceVoy = new AppRole("ROLE_AV");
////			AppRole demenag = new AppRole("ROLE_D");
////			AppRole locationV = new AppRole("ROLE_LV");
////			AppRole locationE = new AppRole("ROLE_LE");
//			AppRole super_admin = new AppRole("ROLE_SUPER_ADMIN");
//			AppRole admin = new AppRole("ROLE_ADMIN");
//			AppRole user = new AppRole("ROLE_USER");
//
//
//			//saving Roles
////			sec.saveRole(bayeur);
////			sec.saveRole(agenceImo);
////			sec.saveRole(agentImo);
////			sec.saveRole(agenceVoy);
////			sec.saveRole(demenag);
////			sec.saveRole(locationV);
////			sec.saveRole(locationE);
//			sec.saveRole(super_admin);
//			sec.saveRole(admin);
//			sec.saveRole(user);
//
//			//saving Users
//			sec.saveUser(talla);
//			sec.saveUser(donker);
//			sec.saveUser(ric);
////			sec.saveUser(UAEI);
////			sec.saveUser(UAI);
////			sec.saveUser(UAV);
////			sec.saveUser(Ubayeur);
////			sec.saveUser(UD);
////			sec.saveUser(ULE);
////			sec.saveUser(ULV);
//
//
//			//Adding roles to users
//			sec.addRoleToUser(donker.getUsername(), admin.getName());
//			sec.addRoleToUser(ric.getUsername(), user.getName());
//			sec.addRoleToUser(talla.getUsername(), super_admin.getName());
////			sec.addRoleToUser(UAEI.getUsername(), agenceImo.getName());
////			sec.addRoleToUser(UAI.getUsername(), agentImo.getName());
////			sec.addRoleToUser(UAV.getUsername(), agenceVoy.getName());
////			sec.addRoleToUser(UP.getUsername(), prive.getName());
////			sec.addRoleToUser(ULV.getUsername(), locationV.getName());
////			sec.addRoleToUser(ULE.getUsername(), locationE.getName());
//
//			//categories
//			Categorie sale = new Categorie("VENTE");
//			Categorie location = new Categorie("LOCATION");
//
//			//saving categories
//			categorieService.createCategorie(sale);
//			categorieService.createCategorie(location);
//
//			//types
//			Type appartment = new Type("APPARTEMENT");
//			Type studio = new Type("STUDIO");
//			Type chambre = new Type("CHAMBRE");
//			Type villa = new Type("VILLA");
//			Type duplex = new Type("DUPLEX");
//
//			//saving types
//			typeService.createType(appartment);
//			typeService.createType(studio);
//			typeService.createType(chambre);
//			typeService.createType(villa);
//			typeService.createType(duplex);
//
//			//visibilities
//			Visibility approved = new Visibility("APPROVED");
//			Visibility review = new Visibility("REVIEW");
//			Visibility rejected = new Visibility("REJECTED");
//
//			//saving visibilities
//			visibilityService.createVisibility(approved);
//			visibilityService.createVisibility(review);
//			visibilityService.createVisibility(rejected);
//
//			////			//villes
//			////			Ville douala = new Ville("Douala");
//			////			Ville yaoundé = new Ville("Yaoundé");
//			////			Ville bafoussam = new Ville("Bafoussam");
//			////			Ville dschang = new Ville("Dschang");
//			////			Ville ngaoundére = new Ville("Ngaoundére");
//			////			Ville garoua = new Ville("Garoua");
//			////			Ville maroua = new Ville("Maroua");
//			////			Ville ebolowa = new Ville("Ebolowa");
//			////			Ville bertoua = new Ville("Bertoua");
//			////			Ville buea = new Ville("Buea");
//			////			Ville bamenda = new Ville("Bamenda");
//			////			Ville paris = new Ville("Paris");
//			////			Ville lyon = new Ville("Lyon");
//			////			Ville marseille = new Ville("Marseille");
//			////			Ville toulouse = new Ville("Toulouse");
//			////			Ville nice = new Ville("Nice");
//			////			Ville toronto = new Ville("Toronto");
//			////			Ville ottawa = new Ville("Ottawa");
//			////			Ville quebec = new Ville("Quebec");
//			////			Ville montréal = new Ville("Montréal");
//			//
//			////			//saving villes
//			////			douala= villeService.createVille(douala);
//			////			bafoussam = villeService.createVille(bafoussam);
//			////			yaoundé = villeService.createVille(yaoundé);
//			////			dschang =  villeService.createVille(dschang);
//			////			garoua = villeService.createVille(garoua);
//			////			ngaoundére = villeService.createVille(ngaoundére);
//			////			maroua = villeService.createVille(maroua);
//			//
//			////			Arrondissement dla_i = null, yde_i = null, baf_i = null;
//			////			//Arrondissement : creating and saving
//			////			for (int i = 1; i<=5 ; i++){
//			////				dla_i = new Arrondissement("Douala "+i);
//			////				dla_i.setVille(douala);
//			////				yde_i = new Arrondissement("Yaoundé "+i);
//			////				yde_i.setVille(yaoundé);
//			////				baf_i = new Arrondissement("Bafoussam "+i);
//			////				baf_i.setVille(bafoussam);
//			////				dla_i =  arrondissementService.createArrondissement(dla_i);
//			////				yde_i = arrondissementService.createArrondissement(yde_i);
//			////				baf_i = arrondissementService.createArrondissement(baf_i);
//			////			}
//			//
//			//			//quelques quartiers
//			//
//			////			Bonanjo.setArrondissement(dla_i);
//			////			mvog_ada.setArrondissement(yde_i);
//			////			Bonapriso.setArrondissement(dla_i);
//			////			Soa.setArrondissement(yde_i);
//
//			quartierService.createQuartier(mvog_ada);
//			quartierService.createQuartier(Bonanjo);
//			quartierService.createQuartier(Essos);
//			quartierService.createQuartier(Soa);
//			quartierService.createQuartier(Deido);
//			quartierService.createQuartier(Bonapriso);
//
//
//			quartierService.createQuartier(Mimboman);
//			quartierService.createQuartier(Mendong);
//			quartierService.createQuartier(Bessengué);
//			//
//			////			Quartier Babylone = new Quartier("Babylone");
//			////			Quartier prison_new_bel = new Quartier("Prison New Bel");
//			////			Quartier lagos_market = new Quartier("Lagos Market");
//			////			Quartier Ndokoti = new Quartier("Ndokoti");
//			////			Quartier Yassa = new Quartier("Yassa");
//			////			Quartier Nyalla = new Quartier("Nyalla");
//			////			Quartier Nylon = new Quartier("Nylon");
//			////			Quartier Japoma = new Quartier("Japoma");
//			////			Quartier Logbessou = new Quartier("Logbessou");
//			////			Quartier Logbaba = new Quartier("Logbaba");
//			////			Quartier Bonabéri = new Quartier("Bonabéri");
//			////			Quartier Ndobo = new Quartier("Ndobo");
//			////			Quartier Bonassama = new Quartier("Bonassama");
//			////			Quartier grand_hangar = new Quartier("Grand Hangar");
//			////			Quartier bonjongo = new Quartier("Bonjongo");
//			////			Quartier Bépanda = new Quartier("Bépanda");
//			////			Quartier Bonamoussadi = new Quartier("Bonamoussadi");
//			////			Quartier Bastos = new Quartier("Bastos");
//			////			Quartier Tsinga = new Quartier("Tsinga");
//			////			Quartier Dakar = new Quartier("Dakar");
//			//
//			//
//			//
//			////
//			////			Bonanjo.setArrondissement(dla_i);
//			////			Soa.setArrondissement(dla_i);
//			////
//			////			Bonanjo.setArrondissement(dla_i);
//			////			Bonanjo.setArrondissement(dla_i);
//			////			Bonanjo.setArrondissement(yde_i);
//			//
//			//			//saving quartiers
//			//
//			////
//			////			//annonces
//			////			Annonce annonce1 = new Annonce(
//			////					null,
//			////					donker,
//			////					location,
//			////					villa,
//			////					Soa,
//			////					approved,
//			////					new ArrayList<>(),
//			////					10000.0,
//			////					1,
//			////					8,
//			////					"Mon appartement est un T. 3, c'est-à-dire qu'il y a trois pièces principales pour vivre : un salon et deux chambres. Il y a bien sûr une cuisine, des toilettes et une salle de bains. Mon appartement fait 61 m2",
//			////					Soa.getName(),
//			////					"Urban City",
//			////					1,
//			////					5,
//			////					2,
//			////					150,
//			////					100,
//			////					1,
//			////					new Date()
//			//////					new SimpleDateFormat("hh:mm:ss a").format(new Date())
//			////			);
//			////			Annonce annonce2= new Annonce(
//			////					null,
//			////					donker,
//			////					location,
//			////					villa,
//			////					mvog_ada,
//			////					approved,
//			////					new ArrayList<>(),
//			////					10000.0,
//			////					1,
//			////					8,
//			////					"Mon appartement est un T. 3, c'est-à-dire qu'il y a trois pièces principales pour vivre : un salon et deux chambres. Il y a bien sûr une cuisine, des toilettes et une salle de bains. Mon appartement fait 61 m2",
//			////					Soa.getName(),
//			////					"Urban City",
//			////					1,
//			////					5,
//			////					2,
//			////					150,
//			////					100,
//			////					1,
//			////					new Date()
//			////			);
//			////			annonceService.createAnnonce(annonce1);
//			////			annonceService.createAnnonce(annonce2);
//		};
//
//	}
}
