package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Metier;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainMetierDto {
	
	private Integer idMetier;
	
	private String nomMetier;
	
	public static PlainMetierDto from(Metier metier) {
		PlainMetierDto metierDto = new PlainMetierDto();
		metierDto.setIdMetier(metier.getIdMetier());
		metierDto.setNomMetier(metier.getNomMetier());
		return metierDto;
	}
}
