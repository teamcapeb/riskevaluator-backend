package fr.capeb.backend.riskevaluator.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.capeb.backend.riskevaluator.model.ScoreCategory;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryPK;
import fr.capeb.backend.riskevaluator.model.exception.ScoreCategoryNotFoundException;
import fr.capeb.backend.riskevaluator.repository.ScoreCategorieRepository;

@Service
public class ScoreCategorieService {
	
	private final ScoreCategorieRepository scoreCategoryRepository;
	
	@Autowired
	public ScoreCategorieService(ScoreCategorieRepository scoreCategoryRepository) {
		this.scoreCategoryRepository = scoreCategoryRepository;
	}
	
	public ScoreCategory addScoreCategory(ScoreCategory scoreCategory) {
		return scoreCategoryRepository.save(scoreCategory);
	}
	
	public List<ScoreCategory> getScoreCategorys() {
		return new ArrayList<>(scoreCategoryRepository.findAll());
	}
	
	public ScoreCategory getScoreCategory(ScoreCategoryPK id) {
		return scoreCategoryRepository.findById(id).orElseThrow(() ->
				new ScoreCategoryNotFoundException(id.getIdCategorie()));
	}
	
	public ScoreCategory deleteScoreCategory(ScoreCategoryPK id) {
		ScoreCategory scoreCategory = getScoreCategory(id);
		scoreCategoryRepository.delete(scoreCategory);
		return scoreCategory;
	}
	
	@Transactional
	public ScoreCategory editScoreCategory(ScoreCategoryPK id, ScoreCategory scoreCategory) {
		ScoreCategory scoreCategoryToEdit = getScoreCategory(id);
		scoreCategoryToEdit.setCategorieQuestion(scoreCategory.getCategorieQuestion());
		scoreCategoryToEdit.setKey(scoreCategory.getKey());
		scoreCategoryToEdit.setEvaluation(scoreCategory.getEvaluation());
		scoreCategoryToEdit.setNbPoints(scoreCategory.getNbPoints());
		return scoreCategoryRepository.save(scoreCategoryToEdit);
	}
	
}
