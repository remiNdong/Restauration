package fr.restauration.service;

import java.util.List;

import fr.restauration.model.Favoris;


public interface FavorisService {

	Favoris enregistrer(Favoris favoris);
	
	List<Favoris> lister();
	
	void supprimer(Long id);
}
