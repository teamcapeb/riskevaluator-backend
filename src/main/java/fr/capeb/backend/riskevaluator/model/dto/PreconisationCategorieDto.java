package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.PreconisationCategorie;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PreconisationCategorieDto {
	
	private Integer idPreconisation;
	
	private PlainCategorieQuestionDto categorieQuestion;
	
	private String contenu;
	
	private Integer viewIfPourcentageScoreLessThan;
	
	public static PreconisationCategorieDto from(PreconisationCategorie preconisationCategorie) {
		PreconisationCategorieDto preconisationCategorieDto = new PreconisationCategorieDto();
		preconisationCategorieDto.setIdPreconisation(preconisationCategorie.getIdPreconisation());
		PlainCategorieQuestionDto plainCategorieQuestionDto = new PlainCategorieQuestionDto();
		CategorieQuestion categorieQuestion1 = preconisationCategorie.getCategorieQuestion();
		plainCategorieQuestionDto.setIdCategorie(categorieQuestion1.getIdCategorie());
		plainCategorieQuestionDto.setLibelle(categorieQuestion1.getLibelle());
		preconisationCategorieDto.setCategorieQuestion(plainCategorieQuestionDto);
		preconisationCategorieDto.setContenu(preconisationCategorie.getContenu());
		preconisationCategorieDto.setViewIfPourcentageScoreLessThan(preconisationCategorie.getViewIfPourcentageScoreLessThan());
		return preconisationCategorieDto;
	}
}
