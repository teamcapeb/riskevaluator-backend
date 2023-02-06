package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.Question;
import fr.capeb.backend.riskevaluator.model.Reponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ReponseDto {
	
	private Integer idReponse;
	
	private PlainQuestionDto question;
	
	private Integer nbPoints;
	
	private String contenu;
	
	public static ReponseDto from(Reponse reponse) {
		ReponseDto reponseDto = new ReponseDto();
		reponseDto.setIdReponse(reponse.getIdReponse());
		reponseDto.setContenu(reponse.getContenu());
		Question question = reponse.getQuestion();
		PlainQuestionDto plainQuestionDto = new PlainQuestionDto();
		
		plainQuestionDto.setTypeQuestion(question.getTypeQuestion());
		plainQuestionDto.setScoreMaxPossibleQuestion(question.getScoreMaxPossibleQuestion());
		CategorieQuestion categorieQuestion = question.getCategorieQuestion();
		PlainCategorieQuestionDto plainCategorieQuestionDto = new PlainCategorieQuestionDto();
		plainCategorieQuestionDto.setLibelle(categorieQuestion.getLibelle());
		plainCategorieQuestionDto.setIdCategorie(categorieQuestion.getIdCategorie());
		plainQuestionDto.setIdQuestion(question.getIdQuestion());
		plainQuestionDto.setLibelleQuestion(question.getLibelleQuestion());
		
		reponseDto.setQuestion(plainQuestionDto);
		reponseDto.setNbPoints(reponse.getNbPoints());
		return reponseDto;
	}
	
}
