package fr.restauration.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.restauration.model.Restaurant;
import fr.restauration.outils.EnsemblePage;
import fr.restauration.service.RestaurantService;
import fr.restauration.utils.RestaurantFacadeI;
import fr.restauration.utils.RestaurantFacadeImpl;

@Controller
public class RestaurantsController {

	@Autowired
	RestaurantService restaurantService;

	RestaurantFacadeI facade;

	// mapping pour methode Get
	@RequestMapping("/show")
	public String viewTemplate(Model model, HttpSession session, HttpServletRequest request) {

		List<Restaurant> catalogBDD = null;
		EnsemblePage<Restaurant> ensemblePage=null;
		Object o =  session.getAttribute("ensemblePage");
		if(o!=null)
			 ensemblePage=(EnsemblePage<Restaurant>)o;

		if (ensemblePage == null) {

			// on recupere ici la liste des restaurants sur le net au cas ou elle aurait
			// changé
			try {
				facade = new RestaurantFacadeImpl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Collection<Restaurant> catalogJson = facade.getListeRestaurants();
			catalogBDD = restaurantService.lister();

			// si des restaurants ont été ajoutés au site depuis la derniere visite, on les
			// ajoute a la BDD
			if (catalogJson.size() != catalogBDD.size()) {

				for (Restaurant restaurant : catalogJson) {

					try {
						if (!catalogBDD.contains(restaurant))
							restaurantService.enregistrer(restaurant);

					} catch (Exception e) {
						System.out.println("Erreur insertion Restaurant :" + restaurant);
					}
				}

				// on recupere la nouvelle version des restaurants apres ajouts des eventuels
				// nouveaux restaurants
				catalogBDD = restaurantService.lister();

			}
			ensemblePage = new EnsemblePage<Restaurant>(catalogBDD);
		}

		List<Integer> listEtoiles = restaurantService.listeEtoiles();
		model.addAttribute("listEtoiles", listEtoiles);

		List<String> listVilles = restaurantService.listeVilles();
		model.addAttribute("listVilles", listVilles);

		String indexPageString = request.getParameter("indexPage");
		int indexPage = 1;

		if (indexPageString != null)
			indexPage = Integer.parseInt(indexPageString);

		List<Restaurant> listResto = ensemblePage.getPage(indexPage);

		int taille = ensemblePage.taille();
		// objet model permet d'inserer des attributs dans la vue et les recuperer
		model.addAttribute("catalog", listResto);
		model.addAttribute("taille", taille);
		return "showCatalog";
	}

	// mapping pour methode Post
	@PostMapping("/show")
	public String viewTemplateBis(Model model, HttpSession session, HttpServletRequest request) {

		String etoiles = request.getParameter("etoiles");
		String ville = request.getParameter("ville");
		List<Restaurant> catalogBDD = null;

		if (!etoiles.equals("none") && !ville.equals("none")) {

			int nbEtoiles = Integer.parseInt(etoiles);

			catalogBDD = restaurantService.listerParEtoilesVille(nbEtoiles, ville);
		} else if (!etoiles.equals("none")) {

			int nbEtoiles = Integer.parseInt(etoiles);
			catalogBDD = restaurantService.listerParEtoiles(nbEtoiles);
		} else if (!ville.equals("none")) {
			catalogBDD = restaurantService.listerParVille(ville);
		} else {
			catalogBDD = restaurantService.lister();
		}

		List<String> listVilles = restaurantService.listeVilles();
		model.addAttribute("listVilles", listVilles);

		List<Integer> listEtoiles = restaurantService.listeEtoiles();
		model.addAttribute("listEtoiles", listEtoiles);

		EnsemblePage<Restaurant> ensemblePage = new EnsemblePage<Restaurant>(catalogBDD);
		session.setAttribute("ensemblePage", ensemblePage);

		String indexPageString = request.getParameter("indexPage");
		int indexPage = 1;

		if (indexPageString != null)
			indexPage = Integer.parseInt(indexPageString);

		List<Restaurant> listResto = ensemblePage.getPage(indexPage);

		int taille = ensemblePage.taille();
		// objet model permet d'inserer des attributs dans la vue et les recuperer
		model.addAttribute("catalog", listResto);
		model.addAttribute("taille", taille);
		return "showCatalog";
	}

}
