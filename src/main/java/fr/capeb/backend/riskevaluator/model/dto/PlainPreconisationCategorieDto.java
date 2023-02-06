package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.PreconisationCategorie;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainPreconisationCategorieDto {
	
	private Integer idPreconisation;
	
	private String contenu;
	
	private Integer viewIfPourcentageScoreLessThan;
	
	public static PlainPreconisationCategorieDto from(PreconisationCategorie preconisationCategorie) {
		PlainPreconisationCategorieDto preconisationCategorieDto = new PlainPreconisationCategorieDto();
		preconisationCategorieDto.setIdPreconisation(preconisationCategorie.getIdPreconisation());
		preconisationCategorieDto.setContenu(preconisationCategorie.getContenu());
		preconisationCategorieDto.setViewIfPourcentageScoreLessThan(preconisationCategorie.getViewIfPourcentageScoreLessThan());
		return preconisationCategorieDto;
	}
}
