package com.example.imovers;

import com.example.imovers.annonces.Annonce;
import com.example.imovers.annonces.AnnonceService;
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
import java.util.Date;

@SpringBootApplication
public class ImoversApplication {


	public static void main(String[] args) {
		 SpringApplication.run(ImoversApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/* @Bean
	CommandLineRunner run(SecurityService sec , CategorieService categorieService , TypeService typeService, VisibilityService visibilityService , VilleService villeService, ArrondissementService arrondissementService, QuartierService quartierService, AnnonceService annonceService){
		return args -> {

			Quartier Bonanjo = new Quartier("Bonanjo");
			Quartier Bessengué = new Quartier("Bessengué");
			Quartier Bonapriso = new Quartier("Bonapriso");
			Quartier Deido = new Quartier("Deido");
			Quartier Mimboman = new Quartier("Mimboman");

			Quartier mvog_ada = new Quartier("Mvog-Ada");
			Quartier Soa = new Quartier("Soa");
			Quartier Essos = new Quartier("Essos");
			Quartier Mendong = new Quartier("Mendong");



			//users
			AppUser donker =  new AppUser(null , "DJIALEU" , "djialeu@gmail.com" , "donker" , "123456", new ArrayList<>(), new ArrayList<>());
			AppUser ric = new AppUser(null, "PEWO", "pewo@gmail.com" , "ric" , "123456", new ArrayList<>() , new ArrayList<>()   );
			AppUser stone =  new AppUser(null, "SIANI" ,"stone@gmail.com", "stone" , "123456", new ArrayList<>(), new ArrayList<>());
			AppUser boss =  new AppUser(null, "TALLA" , "boss@gmail.com","boss" , "123456", new ArrayList<>(), new ArrayList<>());

			//roles
			AppRole userRole =  new AppRole( "ROLE_USER");
			AppRole partnerRole = new AppRole( "ROLE_PARTNER");
			AppRole adminRole = new AppRole( "ROLE_ADMIN");
			AppRole superAdminRole = new AppRole( "ROLE_SUPER_ADMIN");

			//saving Roles
			sec.saveRole(userRole);
			sec.saveRole(partnerRole);
			sec.saveRole(adminRole);
			sec.saveRole(superAdminRole);

			//saving Users
			sec.saveUser(boss);
			sec.saveUser(donker);
			sec.saveUser(ric);
			sec.saveUser(stone);


			//Adding roles to users
			sec.addRoleToUser(donker.getUsername() , adminRole.getName());
			sec.addRoleToUser(ric.getUsername() , userRole.getName());
			sec.addRoleToUser(stone.getUsername() , partnerRole.getName());
			sec.addRoleToUser(boss.getUsername() , superAdminRole.getName());

			//categories
			Categorie sale = new Categorie("VENTE");
			Categorie location = new Categorie("LOCATION");

			//saving categories
			categorieService.createCategorie(sale);
			categorieService.createCategorie(location);

			//types
			Type appartment = new Type("APPARTEMENT");
			Type studio = new Type("STUDIO");
			Type chambre = new Type("CHAMBRE");
			Type villa = new Type("VILLA");
			Type duplex = new Type("DUPLEX");

			//saving types
			typeService.createType(appartment);
			typeService.createType(studio);
			typeService.createType(chambre);
			typeService.createType(villa);
			typeService.createType(duplex);

			//visibilities
			Visibility approved = new Visibility("APPROVED");
			Visibility review = new Visibility("REVIEW");
			Visibility rejected = new Visibility("REJECTED");

			//saving visibilities
			visibilityService.createVisibility(approved);
			visibilityService.createVisibility(review);
			visibilityService.createVisibility(rejected);

////			//villes
////			Ville douala = new Ville("Douala");
////			Ville yaoundé = new Ville("Yaoundé");
////			Ville bafoussam = new Ville("Bafoussam");
////			Ville dschang = new Ville("Dschang");
////			Ville ngaoundére = new Ville("Ngaoundére");
////			Ville garoua = new Ville("Garoua");
////			Ville maroua = new Ville("Maroua");
////			Ville ebolowa = new Ville("Ebolowa");
////			Ville bertoua = new Ville("Bertoua");
////			Ville buea = new Ville("Buea");
////			Ville bamenda = new Ville("Bamenda");
////			Ville paris = new Ville("Paris");
////			Ville lyon = new Ville("Lyon");
////			Ville marseille = new Ville("Marseille");
////			Ville toulouse = new Ville("Toulouse");
////			Ville nice = new Ville("Nice");
////			Ville toronto = new Ville("Toronto");
////			Ville ottawa = new Ville("Ottawa");
////			Ville quebec = new Ville("Quebec");
////			Ville montréal = new Ville("Montréal");
//
////			//saving villes
////			douala= villeService.createVille(douala);
////			bafoussam = villeService.createVille(bafoussam);
////			yaoundé = villeService.createVille(yaoundé);
////			dschang =  villeService.createVille(dschang);
////			garoua = villeService.createVille(garoua);
////			ngaoundére = villeService.createVille(ngaoundére);
////			maroua = villeService.createVille(maroua);
//
////			Arrondissement dla_i = null, yde_i = null, baf_i = null;
////			//Arrondissement : creating and saving
////			for (int i = 1; i<=5 ; i++){
////				dla_i = new Arrondissement("Douala "+i);
////				dla_i.setVille(douala);
////				yde_i = new Arrondissement("Yaoundé "+i);
////				yde_i.setVille(yaoundé);
////				baf_i = new Arrondissement("Bafoussam "+i);
////				baf_i.setVille(bafoussam);
////				dla_i =  arrondissementService.createArrondissement(dla_i);
////				yde_i = arrondissementService.createArrondissement(yde_i);
////				baf_i = arrondissementService.createArrondissement(baf_i);
////			}
//
//			//quelques quartiers
//
////			Bonanjo.setArrondissement(dla_i);
////			mvog_ada.setArrondissement(yde_i);
////			Bonapriso.setArrondissement(dla_i);
////			Soa.setArrondissement(yde_i);

			quartierService.createQuartier(mvog_ada);
			quartierService.createQuartier(Bonanjo);
			quartierService.createQuartier(Essos);
			quartierService.createQuartier(Soa);
			quartierService.createQuartier(Deido);
			quartierService.createQuartier(Bonapriso);




			quartierService.createQuartier(Mimboman);
			quartierService.createQuartier(Mendong);
			quartierService.createQuartier(Bessengué);
//
////			Quartier Babylone = new Quartier("Babylone");
////			Quartier prison_new_bel = new Quartier("Prison New Bel");
////			Quartier lagos_market = new Quartier("Lagos Market");
////			Quartier Ndokoti = new Quartier("Ndokoti");
////			Quartier Yassa = new Quartier("Yassa");
////			Quartier Nyalla = new Quartier("Nyalla");
////			Quartier Nylon = new Quartier("Nylon");
////			Quartier Japoma = new Quartier("Japoma");
////			Quartier Logbessou = new Quartier("Logbessou");
////			Quartier Logbaba = new Quartier("Logbaba");
////			Quartier Bonabéri = new Quartier("Bonabéri");
////			Quartier Ndobo = new Quartier("Ndobo");
////			Quartier Bonassama = new Quartier("Bonassama");
////			Quartier grand_hangar = new Quartier("Grand Hangar");
////			Quartier bonjongo = new Quartier("Bonjongo");
////			Quartier Bépanda = new Quartier("Bépanda");
////			Quartier Bonamoussadi = new Quartier("Bonamoussadi");
////			Quartier Bastos = new Quartier("Bastos");
////			Quartier Tsinga = new Quartier("Tsinga");
////			Quartier Dakar = new Quartier("Dakar");
//
//
//
////
////			Bonanjo.setArrondissement(dla_i);
////			Soa.setArrondissement(dla_i);
////
////			Bonanjo.setArrondissement(dla_i);
////			Bonanjo.setArrondissement(dla_i);
////			Bonanjo.setArrondissement(yde_i);
//
//			//saving quartiers
//
////
////			//annonces
////			Annonce annonce1 = new Annonce(
////					null,
////					donker,
////					location,
////					villa,
////					Soa,
////					approved,
////					new ArrayList<>(),
////					10000.0,
////					1,
////					8,
////					"Mon appartement est un T. 3, c'est-à-dire qu'il y a trois pièces principales pour vivre : un salon et deux chambres. Il y a bien sûr une cuisine, des toilettes et une salle de bains. Mon appartement fait 61 m2",
////					Soa.getName(),
////					"Urban City",
////					1,
////					5,
////					2,
////					150,
////					100,
////					1,
////					new Date()
//////					new SimpleDateFormat("hh:mm:ss a").format(new Date())
////			);
////			Annonce annonce2= new Annonce(
////					null,
////					donker,
////					location,
////					villa,
////					mvog_ada,
////					approved,
////					new ArrayList<>(),
////					10000.0,
////					1,
////					8,
////					"Mon appartement est un T. 3, c'est-à-dire qu'il y a trois pièces principales pour vivre : un salon et deux chambres. Il y a bien sûr une cuisine, des toilettes et une salle de bains. Mon appartement fait 61 m2",
////					Soa.getName(),
////					"Urban City",
////					1,
////					5,
////					2,
////					150,
////					100,
////					1,
////					new Date()
////			);
////			annonceService.createAnnonce(annonce1);
////			annonceService.createAnnonce(annonce2);
		};
	}
 */
}
