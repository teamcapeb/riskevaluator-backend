package fr.capeb.backend.riskevaluator.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.exception.CategorieQuestionNotFoundException;
import fr.capeb.backend.riskevaluator.repository.CategorieQuestionRepository;

@Service
public class CategorieQuestionService {
	
	private final CategorieQuestionRepository categorieQuestionRepository;
	
	@Autowired
	public CategorieQuestionService(CategorieQuestionRepository categorieQuestionRepository) {
		this.categorieQuestionRepository = categorieQuestionRepository;
	}
	
	public CategorieQuestion addCategorieQuestion(CategorieQuestion categorieQuestion) {
		return categorieQuestionRepository.save(categorieQuestion);
	}
	
	public List<CategorieQuestion> getCategorieQuestions() {
		return StreamSupport
				.stream(categorieQuestionRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public CategorieQuestion getCategorieQuestion(Integer id) {
		return categorieQuestionRepository.findById(id).orElseThrow(() ->
				new CategorieQuestionNotFoundException(id));
	}
	
	public CategorieQuestion deleteCategorieQuestion(Integer id) {
		CategorieQuestion categorieQuestion = getCategorieQuestion(id);
		categorieQuestionRepository.delete(categorieQuestion);
		return categorieQuestion;
	}
	
	@Transactional
	public CategorieQuestion editCategorieQuestion(Integer id, CategorieQuestion categorieQuestion) {
		CategorieQuestion categorieQuestionToEdit = getCategorieQuestion(id);
		categorieQuestionToEdit.setIdCategorie(id);
		categorieQuestionToEdit.setQuestions(categorieQuestion.getQuestions());
		categorieQuestionToEdit.setPreconisationsCategorie(categorieQuestion.getPreconisationsCategorie());
		categorieQuestionToEdit.setLibelle(categorieQuestion.getLibelle());
		categorieQuestionToEdit.setQuestionnaire(categorieQuestion.getQuestionnaire());
		categorieQuestionToEdit.setScoreEvaluations(categorieQuestion.getScoreEvaluations());
		return categorieQuestionRepository.save(categorieQuestionToEdit);
	}
	
}
