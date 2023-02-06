package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import fr.capeb.backend.riskevaluator.model.MetierQuestionPK;
import fr.capeb.backend.riskevaluator.model.Question;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MetierQuestionDto {
	
	private MetierQuestionPK key = new MetierQuestionPK();
	
	private PlainQuestionDto questionDto;
	
	private PlainMetierDto metier;
	
	public static MetierQuestionDto from(MetierQuestion metierQuestion) {
		MetierQuestionDto metierQuestionDto = new MetierQuestionDto();
		PlainMetierDto plainMetierDto = new PlainMetierDto();
		Metier metier1 = metierQuestion.getMetier();
		plainMetierDto.setIdMetier(metier1.getIdMetier());
		plainMetierDto.setNomMetier(metier1.getNomMetier());
		metierQuestionDto.setMetier(plainMetierDto);
		PlainQuestionDto plainQuestionDto = new PlainQuestionDto();
		Question question = metierQuestion.getQuestion();
		plainQuestionDto.setIdQuestion(question.getIdQuestion());
		plainQuestionDto.setLibelleQuestion(question.getLibelleQuestion());
		plainQuestionDto.setTypeQuestion(question.getTypeQuestion());
		plainQuestionDto.setScoreMaxPossibleQuestion(question.getScoreMaxPossibleQuestion());
		metierQuestionDto.setQuestionDto(plainQuestionDto);
		metierQuestionDto.setKey(metierQuestion.getKey());
		return metierQuestionDto;
	}
	
}
