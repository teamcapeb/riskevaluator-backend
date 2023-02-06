package fr.capeb.backend.riskevaluator.model.dto;

import java.util.HashSet;
import java.util.Set;

import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MetierDto {
	
	private Integer idMetier;
	
	private String nomMetier;
	
	private Set<MetierQuestion> questions = new HashSet<>();
	
	public static MetierDto from(Metier metier) {
		MetierDto metierDto = new MetierDto();
		metierDto.setIdMetier(metier.getIdMetier());
		metierDto.setNomMetier(metier.getNomMetier());
		metierDto.setQuestions(metier.getQuestions());
		return metierDto;
	}
}
