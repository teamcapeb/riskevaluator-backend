package fr.capeb.backend.riskevaluator.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import fr.capeb.backend.riskevaluator.model.dto.CategorieQuestionDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainEvaluationDto;
import fr.capeb.backend.riskevaluator.model.dto.ScoreCategoryDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "score_category")
public class ScoreCategory {
	
	@EqualsAndHashCode.Include
	@EmbeddedId
	private ScoreCategoryPK key = new ScoreCategoryPK();
	
	@EqualsAndHashCode.Exclude
	@MapsId(value = "idEvaluation")
	@ManyToOne
	@JoinColumn(name = "id_evaluation", referencedColumnName = "id_evaluation")
	private Evaluation evaluation;
	
	@EqualsAndHashCode.Exclude
	@MapsId(value = "idCategorie")
	@ManyToOne
	@JoinColumn(name = "id_categorie", referencedColumnName = "id_categorie")
	private CategorieQuestion categorieQuestion;
	
	@EqualsAndHashCode.Exclude
	@Column(name = "nb_points")
	private Integer nbPoints;
	
	public static ScoreCategory from(ScoreCategoryDto scoreCategoryDto) {
		ScoreCategory scoreCategory = new ScoreCategory();
		CategorieQuestionDto categorieQuestionDto = scoreCategoryDto.getCategorieQuestion();
		CategorieQuestion categorieQuestion = new CategorieQuestion();
		categorieQuestion.setLibelle(categorieQuestionDto.getLibelle());
		categorieQuestion.setIdCategorie(categorieQuestionDto.getIdCategorie());
		Set<PreconisationCategorie> preconisationsCategorie = new HashSet<>();
		categorieQuestionDto.getPreconisationsCategorie().forEach(plainPreconisationCategorieDto -> {
			PreconisationCategorie preconisationCategorie = new PreconisationCategorie();
			preconisationCategorie.setContenu(plainPreconisationCategorieDto.getContenu());
			preconisationCategorie.setIdPreconisation(plainPreconisationCategorieDto.getIdPreconisation());
			preconisationCategorie.setViewIfPourcentageScoreLessThan(plainPreconisationCategorieDto.getViewIfPourcentageScoreLessThan());
			preconisationsCategorie.add(preconisationCategorie);
		});
		categorieQuestion.setPreconisationsCategorie(preconisationsCategorie);
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setIdQuestionnaire(categorieQuestionDto.getQuestionnaire().getIdQuestionnaire());
		questionnaire.setThematique(categorieQuestionDto.getQuestionnaire().getThematique());
		questionnaire.setDate(categorieQuestionDto.getQuestionnaire().getDate());
		categorieQuestion.setQuestionnaire(questionnaire);
		
		Set<ScoreCategory> scoreEvaluations = new HashSet<>();
		categorieQuestionDto.getScoreEvaluations().forEach(plainScoreCategoryDto -> {
			ScoreCategory scoreCategory1 = new ScoreCategory();
			scoreCategory1.setNbPoints(plainScoreCategoryDto.getNbPoints());
			scoreCategory1.setKey(plainScoreCategoryDto.getKey());
			scoreEvaluations.add(scoreCategory1);
		});
		categorieQuestion.setScoreEvaluations(scoreEvaluations);
		scoreCategory.setCategorieQuestion(categorieQuestion);
		scoreCategory.setKey(scoreCategoryDto.getKey());
		scoreCategory.setNbPoints(scoreCategoryDto.getNbPoints());
		PlainEvaluationDto plainEvaluationDto = scoreCategoryDto.getEvaluation();
		Evaluation evaluation1 = new Evaluation();
		evaluation1.setIdEvaluation(plainEvaluationDto.getIdEvaluation());
		evaluation1.setScoreGeneraleEvaluation(plainEvaluationDto.getScoreGeneraleEvaluation());
		scoreCategory.setEvaluation(evaluation1);
		return scoreCategory;
	}
	
}
