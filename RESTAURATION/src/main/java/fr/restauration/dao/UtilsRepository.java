package fr.restauration.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.restauration.model.Restaurant;

public class UtilsRepository  {
	
	 @PersistenceContext
	    private EntityManager entityManager;
	
	
	
	
	public Iterable<String> getVilles(){
		List<String> listVilles=entityManager.createNativeQuery("select distinct  r.ville from Restaurant r where r.ville <> '' and r.ville is not null").getResultList();
		return listVilles;
	}
	

}
