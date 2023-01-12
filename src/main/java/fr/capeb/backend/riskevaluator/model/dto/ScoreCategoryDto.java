package fr.capeb.backend.riskevaluator.model.dto;


import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.ScoreCategory;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryPK;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ScoreCategoryDto {
    private ScoreCategoryPK key = new ScoreCategoryPK();
    private PlainEvaluationDto evaluation;
    private PlainCategorieQuestionDto categorieQuestion;
    private Integer nbPoints;

    public static ScoreCategoryDto from(ScoreCategory scoreCategory){
        ScoreCategoryDto scoreCategoryDto = new ScoreCategoryDto();
        CategorieQuestion categorieQuestion1 = scoreCategory.getCategorieQuestion();
        PlainCategorieQuestionDto plainCategorieQuestionDto1 = new PlainCategorieQuestionDto();
        plainCategorieQuestionDto1.setLibelle(categorieQuestion1.getLibelle());
        plainCategorieQuestionDto1.setIdCategorie(categorieQuestion1.getIdCategorie());
        scoreCategoryDto.setCategorieQuestion(plainCategorieQuestionDto1);
        scoreCategoryDto.setKey(scoreCategory.getKey());
        scoreCategoryDto.setNbPoints(scoreCategory.getNbPoints());
        PlainEvaluationDto plainEvaluationDto = new PlainEvaluationDto();
        Evaluation evaluation = scoreCategory.getEvaluation();
        plainEvaluationDto.setIdEvaluation(evaluation.getIdEvaluation());
        plainEvaluationDto.setScoreGeneraleEvaluation(evaluation.getScoreGeneraleEvaluation());
        scoreCategoryDto.setEvaluation(plainEvaluationDto);
        return scoreCategoryDto;
    }

}
