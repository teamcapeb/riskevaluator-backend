package fr.capeb.backend.riskevaluator.model.dto;

import java.util.Date;

import fr.capeb.backend.riskevaluator.model.Questionnaire;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainQuestionnaireDto {
	
	private Integer idQuestionnaire;
	
	private String thematique;
	
	private Date date;
	
	public static PlainQuestionnaireDto from(Questionnaire questionnaire) {
		PlainQuestionnaireDto plainQuestionnaireDto = new PlainQuestionnaireDto();
		plainQuestionnaireDto.setIdQuestionnaire(questionnaire.getIdQuestionnaire());
		plainQuestionnaireDto.setThematique(questionnaire.getThematique());
		return plainQuestionnaireDto;
	}
	
}
