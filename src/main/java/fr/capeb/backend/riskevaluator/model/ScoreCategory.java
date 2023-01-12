package fr.capeb.backend.riskevaluator.model;


import fr.capeb.backend.riskevaluator.model.dto.PlainCategorieQuestionDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainEvaluationDto;
import fr.capeb.backend.riskevaluator.model.dto.ScoreCategoryDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public static ScoreCategory from(ScoreCategoryDto scoreCategoryDto){
        ScoreCategory scoreCategory = new ScoreCategory();
        PlainCategorieQuestionDto plainCategorieQuestionDto = scoreCategoryDto.getCategorieQuestion();
        CategorieQuestion categorieQuestion = new CategorieQuestion();
        categorieQuestion.setLibelle(plainCategorieQuestionDto.getLibelle());
        categorieQuestion.setIdCategorie(plainCategorieQuestionDto.getIdCategorie());
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
