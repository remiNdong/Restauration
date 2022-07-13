package fr.restauration.service;

import java.util.List;

import fr.restauration.model.Notation;


public interface NotationService {
	
	Notation enregistrer(Notation notation);
	
	List<Notation> lister();

}
