package fr.capeb.backend.riskevaluator.model.dto;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import fr.capeb.backend.riskevaluator.model.Question;
import fr.capeb.backend.riskevaluator.model.Reponse;
import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {
	
	private Integer idQuestion;
	
	private QuestionType typeQuestion;
	
	private Integer scoreMaxPossibleQuestion;
	
	private String libelleQuestion;
	
	private PlainCategorieQuestionDto categorieQuestion;
	
	private Set<MetierQuestion> metiers = new LinkedHashSet<>();
	
	private Set<PlainReponseDto> reponses = new HashSet<>();
	
	public static QuestionDto from(Question question) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setIdQuestion(question.getIdQuestion());
		questionDto.setLibelleQuestion(question.getLibelleQuestion());
		CategorieQuestion categorieQuestion1 = question.getCategorieQuestion();
		PlainCategorieQuestionDto plainCategorieQuestionDto = new PlainCategorieQuestionDto();
		plainCategorieQuestionDto.setLibelle(categorieQuestion1.getLibelle());
		plainCategorieQuestionDto.setIdCategorie(categorieQuestion1.getIdCategorie());
		questionDto.setCategorieQuestion(plainCategorieQuestionDto);
		questionDto.setScoreMaxPossibleQuestion(question.getScoreMaxPossibleQuestion());
		questionDto.setTypeQuestion(question.getTypeQuestion());
		questionDto.setMetiers(question.getMetiers());
		Set<Reponse> reponses = question.getReponses();
		Set<PlainReponseDto> reponseDtos = new HashSet<>();
		reponses.forEach(reponse -> {
			PlainReponseDto plainReponseDto = new PlainReponseDto();
			plainReponseDto.setContenu(reponse.getContenu());
			plainReponseDto.setIdReponse(reponse.getIdReponse());
			plainReponseDto.setNbPoints(reponse.getNbPoints());
			reponseDtos.add(plainReponseDto);
		});
		questionDto.setReponses(reponseDtos);
		return questionDto;
	}
}
