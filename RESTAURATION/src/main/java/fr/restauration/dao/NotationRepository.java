package fr.restauration.dao;

import org.springframework.data.repository.CrudRepository;

import fr.restauration.model.Notation;

public interface NotationRepository extends CrudRepository<Notation, Long>{
	
}
