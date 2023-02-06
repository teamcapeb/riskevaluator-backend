package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.model.Questionnaire;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PreconisationGlobaleDto {
	
	private Integer idPreconisationG;
	
	private PlainQuestionnaireDto questionnaire;
	
	private String contenu;
	
	private Integer viewIfPourcentageScoreLessThan;
	
	public static PreconisationGlobaleDto from(PreconisationGlobale preconisationGlobale) {
		PreconisationGlobaleDto preconisationGlobaleDto = new PreconisationGlobaleDto();
		preconisationGlobaleDto.setViewIfPourcentageScoreLessThan(preconisationGlobale.getViewIfPourcentageScoreLessThan());
		preconisationGlobaleDto.setIdPreconisationG(preconisationGlobale.getIdPreconisationG());
		
		PlainQuestionnaireDto plainQuestionnaireDto = new PlainQuestionnaireDto();
		Questionnaire questionnaire1 = preconisationGlobale.getQuestionnaire();
		plainQuestionnaireDto.setIdQuestionnaire(questionnaire1.getIdQuestionnaire());
		plainQuestionnaireDto.setThematique(questionnaire1.getThematique());
		preconisationGlobaleDto.setQuestionnaire(plainQuestionnaireDto);
		preconisationGlobaleDto.setContenu(preconisationGlobale.getContenu());
		return preconisationGlobaleDto;
	}
}
