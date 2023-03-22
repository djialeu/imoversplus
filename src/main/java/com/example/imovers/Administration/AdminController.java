package com.example.imovers.Administration;

import com.example.imovers.annonces.Annonce;
import com.example.imovers.annonces.AnnonceService;
import com.example.imovers.security.AppUser;
import com.example.imovers.security.SecurityServiceImpl;
import com.example.imovers.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("admin-imovers")
public class AdminController {

    @Autowired
    private SecurityServiceImpl userService;
    @Autowired
    private AnnonceService annonceService;
    @GetMapping(path = "")
    public String home(Model model){
        Authentication connectedUser = SecurityContextHolder.getContext().getAuthentication();
        String username = connectedUser.getName();
        List<Annonce> annonceList = annonceService.getAnnonces();
        model.addAttribute("username", username);
        model.addAttribute("annonces", annonceList);

        return "dashboard";
    }

    @GetMapping(path = "/users")
    public String users(Model model){
        Authentication connectedUser = SecurityContextHolder.getContext().getAuthentication();
        String username = connectedUser.getName();
        List<AppUser> usersList = userService.getUsers();

        model.addAttribute("username", username);
        model.addAttribute("usersList", usersList);

        return "users";
    }

    @GetMapping(path = "/login")
    public String login(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() instanceof UserDetails){
            return "redirect: ";
        }
        return "login";
    }

}
