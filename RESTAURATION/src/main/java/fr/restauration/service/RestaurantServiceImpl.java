package fr.restauration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.restauration.dao.RestaurantRepository;
import fr.restauration.model.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Transactional
	@Override
	public Restaurant enregistrer(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Restaurant> lister() {
		return (List<Restaurant>) restaurantRepository.findAll();
	}
	
	//@Transactional(readOnly = true)
	//@Override
	//public List<Restaurant> listerRestaurants() {
	//	return (List<Restaurant>) restaurantRepository.findAllRestaurants();
//	}


	@Transactional(readOnly = true)
	@Override
	public List<Restaurant> listerParVille(String ville) {
		return (List<Restaurant>) restaurantRepository.findByVille(ville);
	}

	@Transactional(readOnly = true)
	@Override
	public Restaurant trouver(String recordid) {
		return restaurantRepository.findById(recordid).orElseThrow();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Restaurant> listerParEtoilesVille(int etoiles, String ville) {
		return (List<Restaurant>) restaurantRepository.findByEtoilesVille(etoiles, ville);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Restaurant> listerParEtoiles(int etoiles) {
		return (List<Restaurant>) restaurantRepository.findByEtoiles(etoiles);
	}

	@Transactional(readOnly = true)
	@Override
	public List<String> listeVilles() {
		return (List<String>) restaurantRepository.getVilles();
	}
	
	
}
