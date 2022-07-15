package fr.restauration.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.restauration.model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, String>{
	
	public Iterable<Restaurant> findByVille(String ville);
	
	
	

}
