package fr.restauration.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.restauration.authentification.User;
import fr.restauration.authentification.UserService;

/*
 * Controller qui donnera accès au compte de l'utilisateur authentifié avec ses infromations personnelles
 * et ses favoris
 */
@Controller
public class MonCompteController {

	@Autowired
	UserService userService;

	// mapping pour methode Get
	@GetMapping("/monCompte")
	public String showDetail(Model model) {

		try {
			
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			User userCompte = userService.findUserById(username);
			
			model.addAttribute("userCompte", userCompte);
			
			
			return "monCompte";

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}
	}

}
