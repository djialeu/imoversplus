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
}
