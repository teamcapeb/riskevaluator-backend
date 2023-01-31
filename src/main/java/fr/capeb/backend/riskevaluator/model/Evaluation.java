package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.dto.EvaluationDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainCategorieQuestionDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainEntrepriseDto;
import fr.capeb.backend.riskevaluator.model.dto.ScoreCategoryDto;
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
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @Column(name = "id_evaluation")
    private Integer idEvaluation;

    @Column(name = "score_generale")
    private Integer scoreGeneraleEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_compte")
    private Compte compte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nosiret", nullable=false)
    private Entreprise entreprise;

    @OneToMany(mappedBy = "evaluation",cascade = CascadeType.ALL)
    private Set<ScoreCategory> scoreCategories=new HashSet<>();

    public static Evaluation from(EvaluationDto evaluationDto){
        Evaluation evaluationEntity = new Evaluation();
        evaluationEntity.setCompte(evaluationDto.getCompte());
        Entreprise entreprise1 = new Entreprise();
        PlainEntrepriseDto entrepriseDto = evaluationDto.getEntreprise();
        entreprise1.setNoSiret(entrepriseDto.getNoSiret());
        entreprise1.setNomEntreprise(entrepriseDto.getNomEntreprise());
        entreprise1.setAnneeDeCreation(entrepriseDto.getAnneeDeCreation());
        entreprise1.setEffectifEntreprise(entrepriseDto.getEffectifEntreprise());
        evaluationEntity.setEntreprise(entreprise1);
        evaluationEntity.setScoreGeneraleEvaluation(evaluationDto.getScoreGeneraleEvaluation());
        Set<ScoreCategory> scoreCategories1 = new HashSet<>();
        Set<ScoreCategoryDto> scoreCategoryDtos = evaluationDto.getScoreCategories();

        scoreCategoryDtos.forEach(plainScoreCategoryDto -> {
            ScoreCategory scoreCategory = new ScoreCategory();
            scoreCategory.setKey(plainScoreCategoryDto.getKey());
            CategorieQuestion categorieQuestion = new CategorieQuestion();
            categorieQuestion.setIdCategorie(scoreCategory.getCategorieQuestion().getIdCategorie());
            categorieQuestion.setLibelle(scoreCategory.getCategorieQuestion().getLibelle());
            scoreCategory.setCategorieQuestion(categorieQuestion);
            scoreCategory.setNbPoints(plainScoreCategoryDto.getNbPoints());
            scoreCategories1.add(scoreCategory);
        });
        evaluationEntity.setScoreCategories(scoreCategories1);
        return evaluationEntity;
    }


}