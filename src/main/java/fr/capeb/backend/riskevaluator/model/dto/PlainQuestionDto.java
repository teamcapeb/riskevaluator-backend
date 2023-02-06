package fr.capeb.backend.riskevaluator.model.dto;

import java.util.HashSet;
import java.util.Set;

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
public class PlainQuestionDto {
	
	private Integer idQuestion;
	
	private QuestionType typeQuestion;
	
	private Integer scoreMaxPossibleQuestion;
	
	private String libelleQuestion;
	
	private Set<PlainReponseDto> reponses = new HashSet<>();
	
	public static PlainQuestionDto from(Question question) {
		PlainQuestionDto questionDto = new PlainQuestionDto();
		questionDto.setIdQuestion(question.getIdQuestion());
		questionDto.setLibelleQuestion(question.getLibelleQuestion());
		questionDto.setScoreMaxPossibleQuestion(question.getScoreMaxPossibleQuestion());
		questionDto.setTypeQuestion(question.getTypeQuestion());
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
