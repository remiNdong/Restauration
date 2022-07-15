package fr.restauration.service;

import java.util.List;

import fr.restauration.model.Restaurant;


public interface RestaurantService {
	

	Restaurant enregistrer(Restaurant restaurant);
	
	List<Restaurant> lister();
	
	List<Restaurant> listerParVille(String ville);
	
	List<Restaurant> listerParEtoilesVille(int etoiles,String ville);
	
	List<Restaurant> listerParEtoiles(int etoiles);
	
	List<String> listeVilles();
	
	List<Integer> listeEtoiles();

	Restaurant trouver(String recordid);
	
	

}
