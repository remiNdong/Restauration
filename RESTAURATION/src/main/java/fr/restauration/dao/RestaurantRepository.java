package fr.restauration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.restauration.model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, String>{
	
	public Iterable<Restaurant> findByVille(String ville);
	
	@Query(value="select r.* from restaurant r",nativeQuery = true)
	public Iterable<Restaurant> findAllRestaurants();
	
	@Query(value="select r.* from restaurant r where r.recordid in(select r2.recordid from restaurant r2 , notation n \r\n"
			+ "where r2.recordid=n.recordid\r\n"
			+ "group by r2.recordid\r\n"
			+ "having avg(n.etoiles)>=:etoiles)",nativeQuery = true)
	public Iterable<Restaurant> findByEtoiles(@Param("etoiles") int etoiles);
	
	@Query(value="select r.* from restaurant r where r.ville=:ville and r.recordid   in(select r2.recordid from restaurant r2 , notation n \r\n"
			+ "where r2.recordid=n.recordid\r\n"
			+ "group by r2.recordid\r\n"
			+ "having avg(n.etoiles)>=:etoiles);",nativeQuery = true)
	public Iterable<Restaurant> findByEtoilesVille(@Param("etoiles") int etoiles, @Param("ville") String  ville);
	
	@Query(value="select distinct  r.ville from Restaurant r where r.ville <> '' and r.ville is not null",nativeQuery = true)
	public Iterable<String> getVilles();
	


}
