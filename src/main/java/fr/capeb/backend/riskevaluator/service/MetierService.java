package fr.capeb.backend.riskevaluator.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.exception.MetierNotFoundException;
import fr.capeb.backend.riskevaluator.repository.MetierRepository;

@Service
public class MetierService {
	
	private final MetierRepository metierRepository;
	
	@Autowired
	public MetierService(MetierRepository metierRepository) {
		this.metierRepository = metierRepository;
	}
	
	public Metier addMetier(Metier metier) {
		metier.setIdMetier(metierRepository.findTopByOrderByIdMetierDesc().getIdMetier() + 1);
		return metierRepository.save(metier);
	}
	
	public List<Metier> getMetiers() {
		return new ArrayList<>(metierRepository.findAll());
	}
	
	public Metier getMetier(Integer id) {
		return metierRepository.findById(id).orElseThrow(() ->
				new MetierNotFoundException(id));
	}
	
	public Metier deleteMetier(Integer id) {
		Metier metier = getMetier(id);
		metierRepository.delete(metier);
		return metier;
	}
	
	@Transactional
	public Metier editMetier(Integer id, Metier metier) {
		Metier metierToEdit = getMetier(id);
		metierToEdit.setNomMetier(metier.getNomMetier());
		metierToEdit.setQuestions(metier.getQuestions());
		return metierRepository.save(metierToEdit);
	}
	
}
