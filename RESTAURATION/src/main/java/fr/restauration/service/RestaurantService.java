package fr.restauration.service;

import java.util.List;

import fr.restauration.model.Restaurant;


public interface RestaurantService {
	

	Restaurant enregistrer(Restaurant restaurant);
	
	List<Restaurant> lister();
	
	List<Restaurant> listerRestaurants();
	
	List<Restaurant> listerParVille(String ville);
	
	List<Restaurant> listerParEtoilesVille(int etoiles,String ville);
	
	List<Restaurant> listerParEtoiles(int etoiles);
	
	List<String> listeVilles();
	
	

	Restaurant trouver(String recordid);
	
	

}
