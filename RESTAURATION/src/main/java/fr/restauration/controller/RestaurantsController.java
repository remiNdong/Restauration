package fr.restauration.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.restauration.model.Restaurant;
import fr.restauration.service.RestaurantService;
import fr.restauration.utils.RestaurantFacadeI;
import fr.restauration.utils.RestaurantFacadeImpl;

@Controller
public class RestaurantsController {
	
	@Autowired
	RestaurantService restaurantService;
	
	RestaurantFacadeI facade;
	
	//mapping pour methode Get
		@GetMapping("/show")
		public String viewTemplate(Model model) {
			
			
			//on recupere ici la liste des restaurants sur le net au cas ou elle aurait changé
			try {
				facade=new RestaurantFacadeImpl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Collection<Restaurant> catalogJson=facade.getListeRestaurants();
			Collection<Restaurant> catalogBDD=restaurantService.lister();
			
			
			//si des restaurants ont été ajoutés au site depuis la derniere visite, on les ajoute a la BDD
			if(catalogJson.size()!=catalogBDD.size()) {
				
				for(Restaurant restaurant : catalogJson) {
					
					try {
					if(!catalogBDD.contains(restaurant))
						restaurantService.enregistrer(restaurant);
					
					}catch(Exception e) {
						System.out.println("Erreur insertion Restaurant :" + restaurant);
					}
				}
					
			}
			
			//on recupere la nouvelle version des restaurants
			catalogBDD=restaurantService.lister();
			
			//objet model permet d'inserer des attributs dans la vue et les recuperer
			model.addAttribute("catalog", catalogBDD);
			return "showCatalog";
		}

}
