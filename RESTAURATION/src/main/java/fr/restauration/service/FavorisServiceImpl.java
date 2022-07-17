package fr.restauration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.restauration.dao.FavorisRepository;
import fr.restauration.dao.NotationRepository;
import fr.restauration.model.Favoris;
import fr.restauration.model.Notation;

@Service
public class FavorisServiceImpl implements FavorisService {

	@Autowired
	private FavorisRepository favorisRepository;
	
	@Transactional
	@Override
	public Favoris enregistrer(Favoris favoris) {
		return favorisRepository.save(favoris);
	}
	
	
	
	@Transactional(readOnly=true)
	@Override
	public List<Favoris> lister() {
		return (List<Favoris>)favorisRepository.findAll();
	}
	
	@Transactional
	@Override
	public void supprimer(Long id) {
		favorisRepository.deleteById(id);
	}
}
