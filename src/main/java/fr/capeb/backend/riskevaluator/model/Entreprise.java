package fr.capeb.backend.riskevaluator.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import fr.capeb.backend.riskevaluator.model.dto.EntrepriseDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainEvaluationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "entreprise")
public class Entreprise {
	
	@Id
	@Basic
	@Column(name = "nosiret")
	public Long noSiret;
	
	@Basic
	@Column(name = "nom_entreprise")
	public String nomEntreprise;
	
	@Basic
	@Column(name = "effectif")
	public Integer effectif;
	
	@Basic
	@Column(name = "anneedecreation")
	public Integer anneeDeCreation;
	
	@OrderBy("idEvaluation")
	@OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
	private Set<Evaluation> evaluations = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "entreprises_metiers")
	private Set<Metier> metiers = new HashSet<>();
	
	public static Entreprise from(EntrepriseDto entrepriseDto) {
		Entreprise entreprise = new Entreprise();
		entreprise.setNomEntreprise(entrepriseDto.getNomEntreprise());
		entreprise.setNoSiret(entrepriseDto.getNoSiret());
		entreprise.setEffectif(entrepriseDto.getEffectif());
		
		Set<Metier> metiers = new HashSet<>();
		entrepriseDto.getMetiers().forEach(plainMetierDto -> {
			Metier metier = new Metier();
			metier.setIdMetier(plainMetierDto.getIdMetier());
			metier.setNomMetier(plainMetierDto.getNomMetier());
			metiers.add(metier);
		});
		
		entreprise.setMetiers(metiers);
		
		entreprise.setAnneeDeCreation(entrepriseDto.getAnneeDeCreation());
		Set<PlainEvaluationDto> plainEvaluationDtos = entrepriseDto.getEvaluations();
		Set<Evaluation> evaluations1 = new HashSet<>();
		plainEvaluationDtos.forEach(plainEvaluationDto -> {
			Evaluation evaluation = new Evaluation();
			evaluation.setIdEvaluation(plainEvaluationDto.getIdEvaluation());
			evaluation.setScoreGeneraleEvaluation(plainEvaluationDto.getScoreGeneraleEvaluation());
			evaluations1.add(evaluation);
		});
		
		entreprise.setEvaluations(evaluations1);
		return entreprise;
	}
	
}
