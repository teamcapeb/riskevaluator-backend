package fr.capeb.backend.riskevaluator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.capeb.backend.riskevaluator.model.Questionnaire;
import fr.capeb.backend.riskevaluator.model.dto.CategorieQuestionDto;
import fr.capeb.backend.riskevaluator.model.exception.QuestionnaireNotFoundException;
import fr.capeb.backend.riskevaluator.repository.QuestionRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {
	
	private final QuestionnaireRepository questionnaireRepository;
	
	private final QuestionRepository questionRepository;
	
	public Questionnaire addQuestionnaire(Questionnaire questionnaire) {
		return questionnaireRepository.save(questionnaire);
	}
	
	public List<Questionnaire> getQuestionnaires() {
		return new ArrayList<>(questionnaireRepository.findAll());
	}
	
	public Questionnaire getQuestionnaire(Integer id) {
		return questionnaireRepository.findById(id).orElseThrow(() ->
				new QuestionnaireNotFoundException(id));
	}
	
	public Questionnaire deleteQuestionnaire(Integer id) {
		Questionnaire questionnaire = getQuestionnaire(id);
		questionnaireRepository.delete(questionnaire);
		return questionnaire;
	}
	
	public List<Questionnaire> byMetierIds(Set<Integer> metierId) {
		return questionnaireRepository.getQuestionnaireByMetiersIds(metierId);
	}
	
	@Transactional
	public Questionnaire editQuestionnaire(Integer id, Questionnaire questionnaire) {
		Questionnaire questionnaireToEdit = getQuestionnaire(id);
		questionnaireToEdit.setThematique(questionnaire.getThematique());
		questionnaireToEdit.setCategorieQuestions(questionnaire.getCategorieQuestions());
		questionnaireToEdit.setPreconisationGlobales(questionnaire.getPreconisationGlobales());
		return questionnaireRepository.save(questionnaireToEdit);
	}
	
	public ResponseEntity<List<CategorieQuestionDto>> getQuestionsByQuestionnaireAndMetierIds(Integer id, Set<Integer> metierId) {
		List<CategorieQuestionDto> categorieQuestionDtos = questionRepository.getCategorieQuestionsByQuestionnaireIdAndMetiers(id, metierId)
				.stream().map(CategorieQuestionDto::from).collect(Collectors.toList());
		
		return new ResponseEntity<>(categorieQuestionDtos, HttpStatus.OK);
	}
	
}
