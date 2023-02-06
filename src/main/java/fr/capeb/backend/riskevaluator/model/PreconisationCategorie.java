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

import fr.capeb.backend.riskevaluator.model.dto.PlainCategorieQuestionDto;
import fr.capeb.backend.riskevaluator.model.dto.PreconisationCategorieDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "preconisation_categorie")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PreconisationCategorie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_preconisation")
	private Integer idPreconisation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categorie", nullable = false)
	private CategorieQuestion categorieQuestion;
	
	@Column(name = "contenu", columnDefinition = "TEXT")
	private String contenu;
	
	@NotNull
	@Column(name = "viewifpourcentagescorelessthan")
	private Integer viewIfPourcentageScoreLessThan;
	
	public static PreconisationCategorie from(PreconisationCategorieDto preconisationCategorieDto) {
		PreconisationCategorie preconisationCategorie = new PreconisationCategorie();
		CategorieQuestion categorieQuestion1 = new CategorieQuestion();
		PlainCategorieQuestionDto plainCategorieQuestionDto = preconisationCategorieDto.getCategorieQuestion();
		categorieQuestion1.setIdCategorie(plainCategorieQuestionDto.getIdCategorie());
		categorieQuestion1.setLibelle(plainCategorieQuestionDto.getLibelle());
		preconisationCategorie.setCategorieQuestion(categorieQuestion1);
		preconisationCategorie.setContenu(preconisationCategorieDto.getContenu());
		preconisationCategorie.setViewIfPourcentageScoreLessThan(preconisationCategorieDto.getViewIfPourcentageScoreLessThan());
		return preconisationCategorie;
	}
	
}


