package fr.capeb.backend.riskevaluator.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.exception.EvaluationNotFoundException;
import fr.capeb.backend.riskevaluator.repository.EvaluationRepository;

@Service
public class EvaluationService {
	
	private final EvaluationRepository evaluationRepository;
	
	@Autowired
	public EvaluationService(EvaluationRepository evaluationRepository) {
		this.evaluationRepository = evaluationRepository;
	}
	
	public Evaluation addEvaluation(Evaluation evaluation) {
		Integer newId = evaluationRepository.findTopByOrderByIdEvaluationDesc().getIdEvaluation() + 1;
		evaluation.setIdEvaluation(newId);
		
		evaluation.setMetiers(evaluation.getMetiers());
		
		evaluation.getScoreCategories().forEach((scoreCategory -> {
			scoreCategory.getEvaluation().setIdEvaluation(newId);
		}));
		return evaluationRepository.save(evaluation);
	}
	
	public List<Evaluation> getEvaluations() {
		return new ArrayList<>(evaluationRepository.findAll());
	}
	
	public Evaluation getEvaluation(Integer id) {
		return evaluationRepository.findById(id).orElseThrow(() ->
				new EvaluationNotFoundException(id));
	}
	
	public Evaluation deleteEvaluation(Integer id) {
		Evaluation evaluation = getEvaluation(id);
		evaluationRepository.delete(evaluation);
		return evaluation;
	}
	
	@Transactional
	public void addMetier(Integer evaluationId, Metier metier) {
		Evaluation evaluation = getEvaluation(evaluationId);
		evaluation.getMetiers().add(metier);
		metier.getEvaluations().add(evaluation);
		evaluationRepository.save(evaluation);
	}
	
	@Transactional
	public Evaluation editEvaluation(Integer id, Evaluation evaluation) {
		Evaluation evaluationToEdit = getEvaluation(id);
		evaluationToEdit.setCompte(evaluation.getCompte());
		evaluationToEdit.setEntreprise(evaluation.getEntreprise());
		evaluationToEdit.setScoreGeneraleEvaluation(evaluation.getScoreGeneraleEvaluation());
		evaluationToEdit.setMetiers(evaluation.getMetiers());
		evaluationToEdit.getMetiers().forEach(metier -> {
			metier.getEvaluations().add(evaluation);
		});
		return evaluationRepository.save(evaluationToEdit);
	}
	
}
