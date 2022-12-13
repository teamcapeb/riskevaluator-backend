package fr.capeb.backend.riskevaluator.model;


import fr.capeb.backend.riskevaluator.model.dto.ScoreCategoryDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
        scoreCategory.setCategorieQuestion(scoreCategoryDto.getCategorieQuestion());
        scoreCategory.setKey(scoreCategoryDto.getKey());
        scoreCategory.setNbPoints(scoreCategoryDto.getNbPoints());
        scoreCategory.setEvaluation(scoreCategoryDto.getEvaluation());
        return scoreCategory;
    }


}
