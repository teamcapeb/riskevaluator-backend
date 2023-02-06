package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Reponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainReponseDto {
	
	private Integer idReponse;
	
	private Integer nbPoints;
	
	private String contenu;
	
	public static PlainReponseDto from(Reponse reponse) {
		PlainReponseDto plainReponseDto = new PlainReponseDto();
		plainReponseDto.setIdReponse(reponse.getIdReponse());
		plainReponseDto.setContenu(reponse.getContenu());
		plainReponseDto.setNbPoints(reponse.getNbPoints());
		return plainReponseDto;
	}
	
}
