package fr.capeb.backend.riskevaluator.model.dto;


import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.ScoreCategory;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryPK;
import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ScoreCategoryDto {
    private ScoreCategoryPK key = new ScoreCategoryPK();
    private Evaluation evaluation;
    private CategorieQuestion categorieQuestion;
    private Integer nbPoints;

    public static ScoreCategoryDto from(ScoreCategory scoreCategory){
        ScoreCategoryDto scoreCategoryDto = new ScoreCategoryDto();
        scoreCategoryDto.setCategorieQuestion(scoreCategory.getCategorieQuestion());
        scoreCategoryDto.setKey(scoreCategory.getKey());
        scoreCategoryDto.setNbPoints(scoreCategory.getNbPoints());
        scoreCategoryDto.setEvaluation(scoreCategory.getEvaluation());
        return scoreCategoryDto;
    }

}
