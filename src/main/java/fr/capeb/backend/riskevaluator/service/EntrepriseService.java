package fr.capeb.backend.riskevaluator.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.projection.ScoreMetierEntreprise;
import fr.capeb.backend.riskevaluator.projection.ScoreMoyenByTailleAndThematique;
import fr.capeb.backend.riskevaluator.projection.ScoreMoyenByTailleAndThematiqueResponse;
import fr.capeb.backend.riskevaluator.projection.ScoreMoyenEntreprise;
import fr.capeb.backend.riskevaluator.repository.EntrepriseRepository;

@Service
public class EntrepriseService {
	
	private final EntrepriseRepository entrepriseRepository;
	
	@Autowired
	public EntrepriseService(EntrepriseRepository entrepriseRepository) {
		this.entrepriseRepository = entrepriseRepository;
	}
	
	public Entreprise addEntreprise(Entreprise entreprise) {
		entreprise.getMetiers().forEach(metier -> {
			metier.getEntreprises().add(entreprise);
		});
		return entrepriseRepository.save(entreprise);
	}
	
	public List<Entreprise> getEntreprises() {
		return new ArrayList<>(entrepriseRepository.findAll());
	}
	
	public Entreprise getEntreprise(Long id) {
		return entrepriseRepository.findById(id).orElse(null);
	}
	
	public Entreprise deleteEntreprise(Long id) {
		Entreprise entreprise = getEntreprise(id);
		entrepriseRepository.delete(entreprise);
		return entreprise;
	}
	
	public boolean exists(Long siret) {
		return entrepriseRepository.existsById(siret);
	}
	
	@Transactional
	public Entreprise editEntreprise(Long id, Entreprise entreprise) {
		Entreprise entrepriseToEdit = getEntreprise(id);
		entrepriseToEdit.setNomEntreprise(entreprise.getNomEntreprise());
		entrepriseToEdit.setEffectif(entreprise.getEffectif());
		entrepriseToEdit.setEvaluations(entreprise.getEvaluations());
		entrepriseToEdit.setAnneeDeCreation(entreprise.getAnneeDeCreation());
		entrepriseToEdit.setMetiers(entreprise.getMetiers());
		return entrepriseRepository.save(entrepriseToEdit);
	}
	
	public List<ScoreMoyenByTailleAndThematique> findScoreMoyenByTailleAndThematique() {
		return entrepriseRepository.findScoreMoyenByTailleAndThematique();
	}
	
	public List<ScoreMoyenEntreprise> findScoreMoyenParEntreprise() {
		return entrepriseRepository.findScoreMoyen();
	}
	
	public List<ScoreMetierEntreprise> findScoreParMetierParEntreprise() {
		return entrepriseRepository.findScoreParMetier();
	}
	
}
