package fr.restauration.service;

import java.util.List;

import fr.restauration.model.Restaurant;


public interface RestaurantService {
	

	Restaurant enregistrer(Restaurant restaurant);
	
	List<Restaurant> lister();
	
	List<Restaurant> listerParVille(String ville);

	Restaurant trouver(String recordid);

}
