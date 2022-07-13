package fr.restauration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.restauration.dao.NotationRepository;
import fr.restauration.model.Notation;

@Service
public class NotationServiceImpl implements NotationService {

	@Autowired
	private NotationRepository notationRepository;
	
	@Transactional
	@Override
	public Notation enregistrer(Notation notation) {
		return notationRepository.save(notation);
	}
	
	
	
	@Transactional(readOnly=true)
	@Override
	public List<Notation> lister() {
		return (List<Notation>)notationRepository.findAll();
	}
}
