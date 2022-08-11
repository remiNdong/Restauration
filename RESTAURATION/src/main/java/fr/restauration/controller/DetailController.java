package fr.restauration.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.restauration.authentification.User;
import fr.restauration.authentification.UserDetailsServiceImpl;
import fr.restauration.authentification.UserRepository;
import fr.restauration.authentification.UserService;
import fr.restauration.model.Favoris;
import fr.restauration.model.Notation;
import fr.restauration.model.Restaurant;
import fr.restauration.service.FavorisService;
import fr.restauration.service.NotationService;
import fr.restauration.service.RestaurantService;

/*
 * Controller qui permettra de voir le detail d'un restaurant, de lui ajouter une notation
 *  et de le mettre ou l'enlever des favoris pour les utilisateurs authentifiés
 */
@Controller
public class DetailController {

	
	
	@Autowired
	RestaurantService restaurantService;

	@Autowired
	NotationService notationService;

	@Autowired
	UserService userService;

	@Autowired
	FavorisService favorisService;
	

	// mapping pour methode Get
	@GetMapping("/showDetail/{id}")
	public String showDetail(Model model, @PathVariable String id, HttpServletRequest request) {

		try {

			Restaurant restaurant = restaurantService.trouver(id);
			model.addAttribute(restaurant);
			Notation notation = new Notation();
			notation.setDate(new Timestamp(System.currentTimeMillis()));
			notation.setRestaurant(restaurant);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			User user = userService.findUserById(username);
			notation.setUser(user);
			notation.setCommentaire("");

			String indexPageString = request.getParameter("indexPage");
			int indexPage = 1;

			if (indexPageString != null)
				indexPage = Integer.parseInt(indexPageString);

			// objet model permet d'inserer des attributs dans la vue et les recuperer
			model.addAttribute("indexPage", indexPage);

			// permettra de savoir si un favoris a ete ajoute ou non pour mettre un message
			// en consequence dans la vue
			String dejFav = request.getParameter("dejaFavori");
			Boolean dejaFavori = null;
			if (dejFav != null)
				dejaFavori = Boolean.parseBoolean(dejFav);

			model.addAttribute("dejaFavori", dejaFavori);


			// permettra de savoir si un favoris a ete supprime ou non pour mettre un
			// message en consequence dans la vue
			String pasFav = request.getParameter("pasDansFavoris");
			Boolean pasDansFavoris = null;
			if (pasFav != null)
				pasDansFavoris = Boolean.parseBoolean(pasFav);

			model.addAttribute("pasDansFavoris", pasDansFavoris);

			model.addAttribute("restaurant", restaurant);
			// model.addAttribute("notations", restaurant.getNotations());
			
			//on fournit une notation vierge au cas ou l'utilisateur voudrait laisser une notation et un commentaire
			model.addAttribute("notation", notation);
			return "showDetail";

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}
	}

	/*
	 * Methode utilisée pour enregister la notation
	 */
	@PostMapping("/showDetail")
	public String newArticle(@ModelAttribute Notation notation, Model model) {

		try {

			System.out.println("Restaurant " + notation.getRestaurant());

			System.out.println("User " + notation.getUser());

			System.out.println("Notation " + notation);

			notationService.enregistrer(notation);

			return "redirect:/showDetail/" + notation.getRestaurant().getRecordid();

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}
	}

	@GetMapping("/addToFavoris/{id}")
	public String addToFavoris(Model model, @PathVariable String id, HttpServletRequest request) {

		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			User user = userService.findUserById(username);
			Favoris favoris = new Favoris();
			Restaurant restaurant = restaurantService.trouver(id);
			String dejaFavori = "";

			if (user.restauraurantFavoris().contains(restaurant)) {
				dejaFavori = "true";

			} else {
				favoris.setUser(user);
				favoris.setRestaurant(restaurant);
				System.out.println("Id du restaurant =" + restaurant.getRecordid());
				favorisService.enregistrer(favoris);
				dejaFavori = "false";
			}

			String indexPageString = request.getParameter("indexPage");
			int indexPage = 1;

			if (indexPageString != null)
				indexPage = Integer.parseInt(indexPageString);

			model.addAttribute("indexPage", indexPage);

			return "redirect:/showDetail/" + restaurant.getRecordid() + "?dejaFavori=" + dejaFavori;

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}

	@GetMapping("/deleteFromFavoris/{id}")
	public String deleteFromFavoris(Model model, @PathVariable String id, HttpServletRequest request) {

		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			User user = userService.findUserById(username);
			Restaurant restaurant = restaurantService.trouver(id);
			String pasDansFavoris = "";

			if (!user.restauraurantFavoris().contains(restaurant)) {
				pasDansFavoris = "true";

			} else {

				Long idFavori = null;

				for (Favoris f : user.getFavoris())
					if (f.getRestaurant().equals(restaurant))
						idFavori = f.getId();

				favorisService.supprimer(idFavori);

				pasDansFavoris = "false";
			}

			String indexPageString = request.getParameter("indexPage");
			int indexPage = 1;

			if (indexPageString != null)
				indexPage = Integer.parseInt(indexPageString);

			model.addAttribute("indexPage", indexPage);

			return "redirect:/showDetail/" + restaurant.getRecordid() + "?pasDansFavoris=" + pasDansFavoris;

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}

}
