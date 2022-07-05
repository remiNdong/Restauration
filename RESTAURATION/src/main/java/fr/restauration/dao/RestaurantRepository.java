package fr.restauration.dao;

import org.springframework.data.repository.CrudRepository;

import fr.restauration.model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, String>{
	
	public Iterable<Restaurant> findByVille(String ville);
}
