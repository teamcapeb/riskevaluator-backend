package fr.capeb.backend.riskevaluator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.capeb.backend.riskevaluator.model.dto.PlainQuestionnaireDto;
import fr.capeb.backend.riskevaluator.model.dto.PreconisationGlobaleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "preconisation_globale")
public class PreconisationGlobale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_preconisation_g")
	private Integer idPreconisationG;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_questionnaire", nullable = false)
	private Questionnaire questionnaire;
	
	@Column(name = "contenu", columnDefinition = "TEXT")
	private String contenu;
	
	@NotNull
	@Column(name = "viewifpourcentagescorelessthan")
	private Integer viewIfPourcentageScoreLessThan;
	
	public static PreconisationGlobale from(PreconisationGlobaleDto preconisationGlobaleDto) {
		PreconisationGlobale preconisationGlobale = new PreconisationGlobale();
		preconisationGlobale.setViewIfPourcentageScoreLessThan(preconisationGlobaleDto.getViewIfPourcentageScoreLessThan());
		Questionnaire questionnaire1 = new Questionnaire();
		PlainQuestionnaireDto plainQuestionnaireDto = preconisationGlobaleDto.getQuestionnaire();
		questionnaire1.setThematique(plainQuestionnaireDto.getThematique());
		questionnaire1.setIdQuestionnaire(plainQuestionnaireDto.getIdQuestionnaire());
		preconisationGlobale.setQuestionnaire(questionnaire1);
		preconisationGlobale.setContenu(preconisationGlobaleDto.getContenu());
		return preconisationGlobale;
	}
	
}
