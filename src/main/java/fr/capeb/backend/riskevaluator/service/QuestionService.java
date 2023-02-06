package fr.capeb.backend.riskevaluator.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.capeb.backend.riskevaluator.model.Question;
import fr.capeb.backend.riskevaluator.model.exception.QuestionNotFoundException;
import fr.capeb.backend.riskevaluator.repository.QuestionRepository;

@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	@Autowired
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	
	public Question addQuestion(Question question) {
		return questionRepository.save(question);
	}
	
	public List<Question> getQuestions() {
		return new ArrayList<>(questionRepository.findAll());
	}
	
	public Question getQuestion(Integer id) {
		return questionRepository.findById(id).orElseThrow(() ->
				new QuestionNotFoundException(id));
	}
	
	public Question deleteQuestion(Integer id) {
		Question question = getQuestion(id);
		questionRepository.delete(question);
		return question;
	}
	
	@Transactional
	public Question editQuestion(Integer id, Question question) {
		Question questionToEdit = getQuestion(id);
		questionToEdit.setLibelleQuestion(question.getLibelleQuestion());
		questionToEdit.setCategorieQuestion(question.getCategorieQuestion());
		questionToEdit.setScoreMaxPossibleQuestion(question.getScoreMaxPossibleQuestion());
		questionToEdit.setTypeQuestion(question.getTypeQuestion());
		questionToEdit.setMetiers(question.getMetiers());
		questionToEdit.setReponses(question.getReponses());
		return questionRepository.save(questionToEdit);
	}
	
}
