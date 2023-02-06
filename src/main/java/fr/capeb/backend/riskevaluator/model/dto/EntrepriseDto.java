package fr.capeb.backend.riskevaluator.model.dto;

import java.util.HashSet;
import java.util.Set;

import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.model.Evaluation;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class EntrepriseDto {
	
	public Long noSiret;
	
	public String nomEntreprise;
	
	public Integer effectif;
	
	public Integer anneeDeCreation;
	
	private Set<PlainEvaluationDto> evaluations = new HashSet<>();
	
	private Set<PlainMetierDto> metiers = new HashSet<>();
	
	public static EntrepriseDto from(Entreprise entrepriseEntity) {
		EntrepriseDto entrepriseDto = new EntrepriseDto();
		entrepriseDto.setNomEntreprise(entrepriseEntity.getNomEntreprise());
		entrepriseDto.setEffectif(entrepriseEntity.getEffectif());
		entrepriseDto.setNoSiret(entrepriseEntity.getNoSiret());
		Set<PlainEvaluationDto> plainEvaluationDto = new HashSet<PlainEvaluationDto>();
		Set<Evaluation> evaluation = entrepriseEntity.getEvaluations();
		evaluation.forEach(evaluation1 -> {
			PlainEvaluationDto plainEvaluationDto1 = new PlainEvaluationDto();
			plainEvaluationDto1.setIdEvaluation(evaluation1.getIdEvaluation());
			plainEvaluationDto1.setScoreGeneraleEvaluation(evaluation1.getScoreGeneraleEvaluation());
			plainEvaluationDto.add(plainEvaluationDto1);
		});
		
		Set<PlainMetierDto> plainMetiers = new HashSet<>();
		entrepriseEntity.getMetiers().forEach(metier -> {
			PlainMetierDto plainMetierDto = new PlainMetierDto();
			plainMetierDto.setNomMetier(metier.getNomMetier());
			plainMetierDto.setIdMetier(metier.getIdMetier());
			plainMetiers.add(plainMetierDto);
		});
		
		// TODO
		entrepriseDto.setMetiers(plainMetiers);
		entrepriseDto.setEvaluations(plainEvaluationDto);
		entrepriseDto.setAnneeDeCreation(entrepriseEntity.getAnneeDeCreation());
		return entrepriseDto;
	}
	
}
