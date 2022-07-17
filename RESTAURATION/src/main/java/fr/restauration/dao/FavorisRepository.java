package fr.restauration.dao;

import org.springframework.data.repository.CrudRepository;

import fr.restauration.model.Favoris;

public interface FavorisRepository extends CrudRepository<Favoris, Long> {

}
